package com.example.reservarestaurantes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reservarestaurantes.databinding.FragmentInserirReservaBinding


/**
 * A simple [Fragment] subclass.
 * Use the [InserirReservaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirReservaFragment : Fragment() {
    private var _binding:FragmentInserirReservaBinding? = null

    private val binding get() =_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirReservaBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inserir_reserva, container, false)
    }

    companion object {

    }
}