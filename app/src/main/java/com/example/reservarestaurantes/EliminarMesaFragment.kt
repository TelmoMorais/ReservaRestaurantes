package com.example.reservarestaurantes

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.reservarestaurantes.databinding.FragmentEliminarMesaBinding
import com.google.android.material.snackbar.Snackbar


class EliminarMesaFragment : Fragment() {

    private var _binding: FragmentEliminarMesaBinding? = null

    private val binding get() = _binding!!

    private lateinit var mesa: Mesas


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarMesaBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_eliminar_mesa, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar

        mesa = EliminarMesaFragmentArgs.fromBundle(requireArguments()).mesa

        binding.textViewEliminarNrMesa.text = mesa.numero_mesa.toString()
        binding.textViewEliminarLugaresMesa.text = mesa.quantidade_lugares.toString()
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminarMesa()
                true
            }
            R.id.action_cancelar -> {
                voltarListaMesas()
                true
            }
            else -> false
        }

    private fun eliminarMesa(){
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.EliminarMesa)
            setMessage(R.string.confirmarEliminarMesa)
            setNegativeButton(android.R.string.cancel,DialogInterface.OnClickListener{ dialogInterface, i ->  })
            setPositiveButton(R.string.eliminar,DialogInterface.OnClickListener{dialogInterface, i -> confirmaEliminarMesa()  })
            show()
        }

    }

    private fun confirmaEliminarMesa(){
        val enderecoMesa = Uri.withAppendedPath(ContentProviderReservas.ENDERECO_MESAS, "${mesa.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoMesa,null,null)

        if (registosEliminados != 1){
            Snackbar.make(
                binding.textViewEliminarNrMesa,
                R.string.erroEliminarMesa,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.mesaEliminadaSucesso, Toast.LENGTH_LONG).show()
        voltarListaMesas()
    }

    private fun voltarListaMesas(){
        val acao = EliminarMesaFragmentDirections.actionEliminarMesaFragmentToListaMesasFragment()
        findNavController().navigate(acao)
    }

}