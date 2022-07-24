package com.example.reservarestaurantes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.reservarestaurantes.databinding.FragmentInserirRefeicaoBinding
import com.google.android.material.snackbar.Snackbar


class InserirRefeicaoFragment : Fragment() {

    private var _binding: FragmentInserirRefeicaoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirRefeicaoBinding.inflate(inflater, container, false)

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
        activity.idMenuAtual =R.menu.menu_edicao
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltarListaRefeicoes()
                true
            }
            else -> false
        }

    fun guardar(){
        val tipo_Refeicao = binding.inputTextRefeicao.text.toString()
        if (tipo_Refeicao.isBlank()){
            binding.inputTextRefeicao.error = getString(R.string.clienteReservaObrigatorio)
            binding.inputTextRefeicao.requestFocus()
            return
        }
        insereRefeicao(tipo_Refeicao)
    }

    private fun insereRefeicao(tipo_Refeicao: String){
        val refeicao = Refeicao(tipo_Refeicao)

        val enderecoRefeicaaoInserida = requireActivity().contentResolver.insert(ContentProviderReservas.ENDERECO_REFEICOES, refeicao.toContentValues())

        if (enderecoRefeicaaoInserida == null){
            Snackbar.make(binding.inputTextRefeicao, R.string.erroGuardarRefeicao, Toast.LENGTH_LONG).show()
            return
        }

        Toast.makeText(requireContext(), R.string.reservaGuardadoSucesso, Toast.LENGTH_LONG).show()
        voltarListaRefeicoes()
    }

    private fun voltarListaRefeicoes(){
        findNavController().navigate(R.id.action_inserirRefeicaoFragment_to_listaRefeicaoFragment)
    }

}