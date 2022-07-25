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
import com.example.reservarestaurantes.databinding.FragmentEliminarClienteBinding
import com.google.android.material.snackbar.Snackbar


class EliminarClienteFragment : Fragment() {

    private var _binding: FragmentEliminarClienteBinding? = null

    private val binding get() = _binding!!

    private lateinit var clientes: Clientes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarClienteBinding.inflate(inflater,container,false)
        return binding.root
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

        clientes = EliminarClienteFragmentArgs.fromBundle(arguments!!).cliente

        binding.textViewEliminarNomeCliente.text = clientes.nome
        binding.textViewEliminarTelefoneCliente.text = clientes.contato_telefonico
        binding.textViewEliminarNifCliente.text = clientes.nif
        binding.textViewEliminarMoradaCliente.text = clientes.morada
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminarCliente()
                true
            }
            R.id.action_cancelar -> {
                voltarListaClientes()
                true
            }
            else -> false
        }

    private fun eliminarCliente(){
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.EliminarCliente)
            setMessage(R.string.confirmarEliminarCliente)
            setNegativeButton(android.R.string.cancel,DialogInterface.OnClickListener{dialogInterface, i ->  })
            setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener{dialogInterface, i -> confirmaEliminarCliente()  })
            show()
        }
    }

    private fun confirmaEliminarCliente(){

        val enderecoCliente = Uri.withAppendedPath(ContentProviderReservas.ENDERECO_CLIENTES, "${clientes.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoCliente, null, null)

        if (registosEliminados != 1){
            Snackbar.make(
                binding.textViewEliminarNomeCliente,
                R.string.erroEliminarCliente,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.clienteEliminadoSucesso, Toast.LENGTH_LONG).show()
        voltarListaClientes()
    }

    private fun voltarListaClientes(){
        val acao = EliminarClienteFragmentDirections.actionEliminarClienteFragmentToListaClientesFragment()
        findNavController().navigate(acao)
    }
}