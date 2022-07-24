package com.example.reservarestaurantes

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.reservarestaurantes.databinding.FragmentInserirReservaBinding
import com.google.android.material.snackbar.Snackbar


class InserirReservaFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding:FragmentInserirReservaBinding? = null

    private val binding get() =_binding!!

    private var loader: CursorLoader? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirReservaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_CLIENTES, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_MESAS, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_REFEICOES, null, this)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual =R.menu.menu_edicao
    }


    companion object {
        const val ID_LOADER_CLIENTES = 0
        const val ID_LOADER_MESAS = 1
        const val ID_LOADER_REFEICOES = 2
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param id The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        if(id == ID_LOADER_CLIENTES){
            loader = CursorLoader(
                requireContext(),
                ContentProviderReservas.ENDERECO_CLIENTES,
                TabelaBDClientes.TODOS_CAMPOS_CIENTES,
                null,
                null,
                "${TabelaBDClientes.CAMPO_NOME}"
            )
        }  else if (id == ID_LOADER_MESAS){
            loader = CursorLoader(
                requireContext(),
                ContentProviderReservas.ENDERECO_MESAS,
                TabelaBDMesas.TODOS_CAMPOS_MESAS,
                null,
                null,
                "${TabelaBDMesas.CAMPO_NUMERO_MESA}"
            )
        }else if (id == ID_LOADER_REFEICOES){
            loader = CursorLoader(
                requireContext(),
                ContentProviderReservas.ENDERECO_REFEICOES,
                TabelaBDRefeicao.TODOS_CAMPOS_REFEICAO,
                null,
                null,
                "${TabelaBDRefeicao.CAMPO_TIPO_REFEICAO}"
            )
        }
        return loader as CursorLoader
    }







    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is *not* allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See [ FragmentManager.openTransaction()][androidx.fragment.app.FragmentManager.beginTransaction] for further discussion on this.
     *
     *
     * This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     *
     *  *
     *
     *The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a [android.database.Cursor]
     * and you place it in a [android.widget.CursorAdapter], use
     * the [android.widget.CursorAdapter.CursorAdapter] constructor *without* passing
     * in either [android.widget.CursorAdapter.FLAG_AUTO_REQUERY]
     * or [android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER]
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     *  *  The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a [android.database.Cursor] from a [android.content.CursorLoader],
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * [android.widget.CursorAdapter], you should use the
     * [android.widget.CursorAdapter.swapCursor]
     * method so that the old Cursor is not closed.
     *
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param loader The Loader that has finished.
     * @param data The data generated by the Loader.
     */
    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        if (loader.id == ID_LOADER_CLIENTES){
            val adapterClientes = SimpleCursorAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                data,
                arrayOf(TabelaBDClientes.CAMPO_NOME),
                intArrayOf(android.R.id.text1),
                0
            )
            binding.spinnerClienteReserva.adapter = adapterClientes

        }else if (loader.id == ID_LOADER_MESAS){
            val adapterMesas = SimpleCursorAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                data,
                arrayOf(TabelaBDMesas.CAMPO_NUMERO_MESA),
                intArrayOf(android.R.id.text1),
                0
            )

            binding.spinnerMesaReserva.adapter = adapterMesas
        } else if (loader.id == ID_LOADER_REFEICOES){
            val adapterRefeicao = SimpleCursorAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                data,
                arrayOf(TabelaBDRefeicao.CAMPO_TIPO_REFEICAO),
                intArrayOf(android.R.id.text1),
                0
            )

            binding.spinnerRefeicaoReserva.adapter = adapterRefeicao
        }



    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    override fun onLoaderReset(loader: Loader<Cursor>) {

        binding.spinnerClienteReserva.adapter = null
        binding.spinnerMesaReserva.adapter = null
        binding.spinnerRefeicaoReserva.adapter = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltarListaReservas()
                true
            }
            else -> false
        }

    private fun guardar(){
        val data =binding.editTextDataReserva.text.toString()
        if(data.isBlank()){
            binding.editTextDataReserva.error = getString(R.string.dataReservaObrigatorio)
            binding.editTextDataReserva.requestFocus()
            return
        }

        val nrPessoas = binding.editTextNrPessoasReserva.text.toString()
        if(nrPessoas.isBlank()){
            binding.editTextNrPessoasReserva.error = getString(R.string.nrPessoasReservaObrigatorio)
            binding.editTextNrPessoasReserva.requestFocus()
            return
        }

        val idCliente = binding.spinnerClienteReserva.selectedItemId
        if(idCliente == Spinner.INVALID_ROW_ID){
            binding.textViewClienteReserva.error = getString(R.string.clienteReservaObrigatorio)
            binding.spinnerClienteReserva.requestFocus()
            return
        }

        val idMesa = binding.spinnerMesaReserva.selectedItemId
        if(idMesa == Spinner.INVALID_ROW_ID){
            binding.textViewMesaReserva.error = getString(R.string.mesaReservaObrigatorio)
            binding.spinnerMesaReserva.requestFocus()
            return
        }

        val idRefeicao = binding.spinnerRefeicaoReserva.selectedItemId
        if(idRefeicao == Spinner.INVALID_ROW_ID){
            binding.textViewRefeicaoReserva.error = getString(R.string.refeicaoReservaObrigatorio)
            binding.spinnerRefeicaoReserva.requestFocus()
            return
        }

        insereReserva(data.toLong(), nrPessoas.toInt(), idCliente, idMesa, idRefeicao)
    }


    private fun insereReserva(data: Long, nrPessoas: Int, idCliente: Long, idMesa: Long, idRefeicao: Long){
        val reserva = Reservas(data, nrPessoas, Clientes(id=idCliente).id, Mesas(id=idMesa).id, Refeicao(id=idRefeicao).id)

        val enderecoReservaInserida = requireActivity().contentResolver.insert(ContentProviderReservas.ENDERECO_RESERVAS, reserva.toContentValues())

        if(enderecoReservaInserida == null){
            Snackbar.make(binding.editTextDataReserva, R.string.erroGuardarReserva, Snackbar.LENGTH_INDEFINITE).show()
            return
        }

        Toast.makeText(requireContext(), R.string.reservaGuardadaSucesso, Toast.LENGTH_LONG).show()
        voltarListaReservas()
    }

    private fun voltarListaReservas(){
        findNavController().navigate(R.id.action_inserirReservaFragment2_to_listaReservasFragment2)
    }
}