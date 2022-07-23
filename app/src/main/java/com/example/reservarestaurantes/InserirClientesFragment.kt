package com.example.reservarestaurantes

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.reservarestaurantes.databinding.FragmentInserirClientesBinding
import com.example.reservarestaurantes.databinding.FragmentInserirReservaBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [InserirClientesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirClientesFragment : Fragment() {
    private var _binding: FragmentInserirClientesBinding? = null

    private val binding get() =_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirClientesBinding.inflate(inflater, container, false)


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
                voltarListaClientes()
                true
            }
            else -> false
        }

    fun guardar(){
        val nomeCliente = binding.inputTextClienteNome.text.toString()
        if (nomeCliente.isBlank()){
           binding.inputTextClienteNome.error = getString(R.string.clienteReservaObrigatorio)
           binding.inputTextClienteNome.requestFocus()
           return
        }

        val telefoneCliente = binding.inputTextClienteTelefone.text.toString()
        if (telefoneCliente.isBlank()){
            binding.inputTextClienteTelefone.error = getString(R.string.clienteReservaObrigatorio)
            binding.inputTextClienteTelefone.requestFocus()
            return
        }

        val nifCliente = binding.inputTextClienteNif.text.toString()
        if (nifCliente.isBlank()){
            binding.inputTextClienteNif.error = getString(R.string.clienteReservaObrigatorio)
            binding.inputTextClienteNif.requestFocus()
            return
        }

        val moradaCliente = binding.inputTextClienteMorada.text.toString()
        if (moradaCliente.isBlank()){
            binding.inputTextClienteMorada.error = getString(R.string.clienteReservaObrigatorio)
            binding.inputTextClienteMorada.requestFocus()
            return
        }

        insereCliente(nomeCliente, telefoneCliente, nifCliente, moradaCliente)
    }

    private fun insereCliente(nomeCliente: String, telefoneCliente: String, nifCliente: String, moradaCliente: String){
        val clientes = Clientes(nomeCliente, telefoneCliente, nifCliente, moradaCliente)

        val enderecoClienteInserido = requireActivity().contentResolver.insert(ContentProviderReservas.ENDERECO_CLIENTES, clientes.toContentValues())

        if (enderecoClienteInserido == null){
            Snackbar.make(binding.inputTextClienteNome, R.string.erroGuardarCliente, Toast.LENGTH_LONG).show()
            return
        }

        Toast.makeText(requireContext(),R.string.clienteGuardadoSucesso, Toast.LENGTH_LONG).show()
        voltarListaClientes()
    }

    private fun voltarListaClientes(){
        findNavController().navigate(R.id.action_inserirClientesFragment_to_listaClientesFragment)
    }
}