package com.example.reservarestaurantes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.reservarestaurantes.databinding.FragmentListaMesasBinding
import com.example.reservarestaurantes.databinding.FragmentListaReservasBinding

class ListaReservasFragment : Fragment(){

    private var _binding: FragmentListaReservasBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaReservasBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // binding.buttonFirst.setOnClickListener {
        //    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
       // }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}