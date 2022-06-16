package com.example.reservarestaurantes

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
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

    private fun insereCliente(db: SQLiteDatabase, cliente: Clientes){
        cliente.id = TabelaBDClientes(db).insert(cliente.toContentValues())
        assertEquals(1, cliente.id)
    }

    private fun insereMesa(db: SQLiteDatabase, mesa: Mesas){
        mesa.id = TabelaBDMesas(db).insert(mesa.toContentValues())
        assertEquals(1,mesa.id)
    }

    private fun insereRefeicao(db: SQLiteDatabase, refeicao: Refeicao){
        refeicao.id = TabelaBDRefeicao(db).insert(refeicao.toContentValues())
        assertEquals(1, refeicao.id)
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

        insereCliente(db, Clientes("Manuel","271297513","186349563","Rua de São Vicente 10, 6300-510 Guarda"))

        db.close()
    }

    @Test
    fun consegueInserirMesas(){

        val db = getWritableDatabase()

       insereMesa(db, Mesas(2,5))


        db.close()
    }


    @Test
    fun consegueInserirRefeicao(){

        val db = getWritableDatabase()

        insereRefeicao(db , Refeicao("Jantar",-1))


        db.close()
    }


    @Test
    fun consegueInserirReserva(){

        val db = getWritableDatabase()

        val cliente = Clientes("João", "924563987", "196348529", "Av. Dr. Francisco Sá Carneiro 50, 6300-559 Guarda", -1)
        insereCliente(db, cliente)

        val mesa = Mesas(5, 4)
        insereMesa(db, mesa)

        val refeicao = Refeicao("Almoço")
        insereRefeicao(db, refeicao)

        val reserva = Reservas(21062022, 5, cliente.id , mesa.id, refeicao.id)
        reserva.id = TabelaBDReservas(db).insert(reserva.toContentValues())


        assertNotEquals(-1,reserva.id)

        db.close()

    }

    @Test
    fun consegueAlterarCliente(){
        val db = getWritableDatabase()

        val cliente = Clientes("Teste", "123456789", "123456789", "Teste")
        insereCliente(db, cliente)

        cliente.nome = "Jose"
        cliente.contato_telefonico = "92497507"
        cliente.nif = "987654321"
        cliente.morada = "Rua"

        val registosAlterados = TabelaBDClientes(db).update(
            cliente.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${cliente.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarMesa(){
        val db = getWritableDatabase()

        val mesa = Mesas(8, 10)
        insereMesa(db, mesa)

        mesa.numero_mesa = 5
        mesa.quantidade_lugares = 4

        val registosAlterados = TabelaBDMesas(db).update(
            mesa.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${mesa.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

}