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
import com.example.reservarestaurantes.databinding.FragmentEliminarRefeicaoBinding
import com.google.android.material.snackbar.Snackbar


class EliminarRefeicaoFragment : Fragment() {

    private var _binding: FragmentEliminarRefeicaoBinding? = null

    private val binding get() = _binding!!

    private lateinit var refeicao: Refeicao


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarRefeicaoBinding.inflate(inflater, container, false)
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

        refeicao = EliminarRefeicaoFragmentArgs.fromBundle(arguments!!).refeicao

        binding.textViewEliminarRefeicao.text = refeicao.tipo_refeicao
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminarRefeicao()
                true
            }
            R.id.action_cancelar -> {
                voltarListaRefeicoes()
                true
            }
            else -> false
        }

    private fun eliminarRefeicao(){
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.EliminarRefeicao)
            setMessage(R.string.confirmarEliminarRefeicao)
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener{dialogInterface, i ->  })
            setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener{dialogInterface, i -> confirmarEliminarRefeicao() })
            show()
        }
    }

    private fun confirmarEliminarRefeicao(){
        val enderecoRefeicao = Uri.withAppendedPath(ContentProviderReservas.ENDERECO_REFEICOES, "${refeicao.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoRefeicao,null,null)

        if (registosEliminados != 1){
            Snackbar.make(
                binding.textViewEliminarTipoRefeicao,
                R.string.erroEliminarRefeicao,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.sucessoEliminarRefeicao, Toast.LENGTH_LONG).show()
        voltarListaRefeicoes()
    }

    private fun voltarListaRefeicoes(){
        val acao = EliminarRefeicaoFragmentDirections.actionEliminarRefeicaoFragmentToListaRefeicaoFragment()
        findNavController().navigate(acao)
    }
}