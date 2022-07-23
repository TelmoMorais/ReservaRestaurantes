package com.example.reservarestaurantes

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.reservarestaurantes.databinding.FragmentInserirMesasBinding
import com.example.reservarestaurantes.databinding.FragmentInserirReservaBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [InserirMesasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirMesasFragment : Fragment() {
    private var _binding: FragmentInserirMesasBinding? = null

    private val binding get() =_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirMesasBinding.inflate(inflater, container, false)

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
                voltarListaMesas()
                true
            }
            else -> false
        }

    private fun guardar(){
        val numeroMesa = binding.inputTextMesaNrMesa.text.toString()
        if (numeroMesa.isBlank()){
            binding.inputTextMesaNrMesa.error = getString(R.string.mesaReservaObrigatorio)
            binding.inputTextMesaNrMesa.requestFocus()
            return
        }

        val lugaresMesa = binding.inputTextMesaLugares.text.toString()
        if (lugaresMesa.isBlank()){
            binding.inputTextMesaLugares.error = getString(R.string.mesaReservaObrigatorio)
            binding.inputTextMesaLugares.requestFocus()
            return
        }

        insereMesa(numeroMesa.toInt(), lugaresMesa.toInt())
    }

    private fun insereMesa(numeroMesa: Int, lugaresMesa: Int){
        val mesas = Mesas(numeroMesa, lugaresMesa)

        val enderecoMesaInserida = requireActivity().contentResolver.insert(ContentProviderReservas.ENDERECO_MESAS, mesas.toContentValues())

        if(enderecoMesaInserida == null){
            Snackbar.make(binding.inputTextMesaNrMesa,R.string.erroGuardarMesa, Toast.LENGTH_LONG).show()
            return
        }

        Toast.makeText(requireContext(),R.string.mesaGuardadaSucesso, Toast.LENGTH_LONG).show()
        voltarListaMesas()
    }

    private fun voltarListaMesas(){
        findNavController().navigate(R.id.action_inserirMesasFragment2_to_listaMesasFragment)
    }
}