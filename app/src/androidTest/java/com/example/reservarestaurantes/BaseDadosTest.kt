package com.example.reservarestaurantes

import android.database.sqlite.SQLiteDatabase
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BaseDadosTest {
    fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase{
        val openHelper = BDReservasRestauranteOpenHelper(appContext())
        return openHelper.writableDatabase
    }


    @Before
    fun apagaBaseDados() {
        appContext().deleteDatabase(BDReservasRestauranteOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados(){
        val openHelper = BDReservasRestauranteOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun consegueInserirClientes(){

        val db = getWritableDatabase()

        val cliente = Clientes("Jose", "924288452", "251099636", "Av. Dr. Francisco Sá Carneiro 50, 6300-559 Guarda")


        db.close()
    }

    @Test
    fun consegueInserirMesas(){

        val db = getWritableDatabase()

        val mesa = Mesas(1, 4)


        db.close()
    }

}