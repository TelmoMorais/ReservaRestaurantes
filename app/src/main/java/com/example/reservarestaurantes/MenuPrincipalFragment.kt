package com.example.reservarestaurantes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.reservarestaurantes.databinding.FragmentMenuPrincipalBinding

class MenuPrincipalFragment : Fragment() {
    private var _binding: FragmentMenuPrincipalBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuPrincipalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonMenuReservas.setOnClickListener{
            findNavController().navigate(R.id.action_menuPrincipalFragment_to_listaReservasFragment2)
        }

        binding.buttonMenuClientes.setOnClickListener{
            findNavController().navigate(R.id.action_menuPrincipalFragment_to_listaClientesFragment)
        }

        binding.buttonMenuMesas.setOnClickListener{
            findNavController().navigate(R.id.action_menuPrincipalFragment_to_listaMesasFragment)
        }
        binding.buttonMenuRefeicoes.setOnClickListener{
            findNavController().navigate(R.id.action_menuPrincipalFragment_to_listaRefeicaoFragment)
        }


        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_main
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_settings -> true
            else -> false
        }
}