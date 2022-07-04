package com.example.reservarestaurantes

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.Person.fromBundle
import androidx.navigation.fragment.findNavController
import com.example.reservarestaurantes.databinding.FragmentEliminarReservaBinding
import com.google.android.material.snackbar.Snackbar


class EliminarReservaFragment : Fragment() {

    private var _binding: FragmentEliminarReservaBinding? = null

    private val binding get() = _binding!!

    private lateinit var reserva: Reservas


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarReservaBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_eliminar_reserva, container, false)
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

        reserva = EliminarReservaFragmentArgs.fromBundle(arguments!!).reserva

        binding.textViewEliminarDataReserva.text = reserva.data_reserva.toString()
        binding.textViewEliminarNrPessoasReserva.text = reserva.numero_pessoas.toString()
        binding.textViewEliminarClienteReserva.text = reserva.clientes_id.toString()
        binding.textViewEliminarMesaReserva.text = reserva.mesas_id.toString()
        binding.textViewEliminarRefeicaoReserva.text = reserva.refeicao_id.toString()
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminarReserva()
                true
            }
            R.id.action_cancelar -> {
                voltarListaReservas()
                true
            }
            else -> false
        }

    private fun eliminarReserva(){
        val enderecoReserva = Uri.withAppendedPath(ContentProviderReservas.ENDERECO_RESERVAS, "${reserva.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoReserva, null, null)

        if (registosEliminados != 1){
            Snackbar.make(
                binding.textViewEliminarDataReserva,
                R.string.erroEliminarReserva,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.reservaEliminadaSucesso, Toast.LENGTH_LONG).show()
        voltarListaReservas()
    }


    private fun voltarListaReservas() {
        val acao = EliminarReservaFragmentDirections.actionEliminarReservaFragmentToListaReservasFragment2()
        findNavController().navigate(acao)
    }
}