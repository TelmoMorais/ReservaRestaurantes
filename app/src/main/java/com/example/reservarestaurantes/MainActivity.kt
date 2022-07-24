package com.example.reservarestaurantes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.reservarestaurantes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    var idMenuAtual = R.menu.menu_main
        get() = field
        set(value) {
            if (value != field) {
                field = value
                invalidateOptionsMenu()
            }
        }

    var menu: Menu? = null


    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(idMenuAtual, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val opcaoProcessada: Boolean

        if (fragment is MenuPrincipalFragment) {
            opcaoProcessada = (fragment as MenuPrincipalFragment).processaOpcaoMenu(item)
        } else if (fragment is ListaReservasFragment) {
            opcaoProcessada = (fragment as ListaReservasFragment).processaOpcaoMenu(item)
        } else if(fragment is InserirReservaFragment){
            opcaoProcessada = (fragment as InserirReservaFragment).processaOpcaoMenu(item)
        } else if (fragment is EliminarReservaFragment) {
            opcaoProcessada = (fragment as EliminarReservaFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaClientesFragment) {
            opcaoProcessada = (fragment as ListaClientesFragment).processaOpcaoMenu(item)
        }else if(fragment is InserirClientesFragment){
            opcaoProcessada = (fragment as InserirClientesFragment).processaOpcaoMenu(item)
        }else if (fragment is EliminarClienteFragment) {
            opcaoProcessada = (fragment as EliminarClienteFragment).processaOpcaoMenu(item)
        }else if (fragment is ListaMesasFragment) {
            opcaoProcessada = (fragment as ListaMesasFragment).processaOpcaoMenu(item)
        }else if(fragment is InserirMesasFragment){
            opcaoProcessada = (fragment as InserirMesasFragment).processaOpcaoMenu(item)
        }else if(fragment is EliminarMesaFragment){
            opcaoProcessada = (fragment as EliminarMesaFragment).processaOpcaoMenu(item)
        }else if(fragment is ListaRefeicaoFragment){
            opcaoProcessada = (fragment as ListaRefeicaoFragment).processaOpcaoMenu(item)
        }else if(fragment is InserirRefeicaoFragment){
            opcaoProcessada = (fragment as InserirRefeicaoFragment).processaOpcaoMenu(item)
        }else if(fragment is EliminarRefeicaoFragment){
            opcaoProcessada = (fragment as EliminarRefeicaoFragment).processaOpcaoMenu(item)
        }else {
            opcaoProcessada = false
        }

        if (opcaoProcessada) return true

        return super.onOptionsItemSelected(item)
    }




    fun mostraOpcoesAlterarEliminar (mostra: Boolean){
        menu!!.findItem(R.id.action_alterar).setVisible(mostra)
        menu!!.findItem(R.id.action_eliminar).setVisible(mostra)
    }
}

