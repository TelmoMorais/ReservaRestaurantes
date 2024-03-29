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
        assertNotEquals(-1, cliente.id)
    }

    private fun insereMesa(db: SQLiteDatabase, mesa: Mesas){
        mesa.id = TabelaBDMesas(db).insert(mesa.toContentValues())
        assertNotEquals(-1,mesa.id)
    }

    private fun insereRefeicao(db: SQLiteDatabase, refeicao: Refeicao){
        refeicao.id = TabelaBDRefeicao(db).insert(refeicao.toContentValues())
        assertNotEquals(-1, refeicao.id)
    }

    private fun insereReserva(db: SQLiteDatabase, reservas: Reservas){
        reservas.id = TabelaBDReservas(db).insert(reservas.toContentValues())
        assertNotEquals(-1, reservas.id)
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

        insereRefeicao(db , Refeicao("Jantar"))


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

    @Test
    fun consegueAlterarRefeicao(){
        val db = getWritableDatabase()

        val refeicao = Refeicao("Almoço")
        insereRefeicao(db, refeicao)

        refeicao.tipo_refeicao = "Jantar"

        val registosAlterados = TabelaBDRefeicao(db).update(
            refeicao.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${refeicao.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }


    @Test
    fun consegueAlterarReservas(){
        val db = getWritableDatabase()

        val cliente1 = Clientes("teste", "183695236", "563282198", "Rua 1")
        insereCliente(db, cliente1)

        val cliente2 = Clientes("João", "924563987", "196348529", "Av. Dr. Francisco Sá Carneiro 50, 6300-559 Guarda")
        insereCliente(db, cliente2)

        val mesa1 = Mesas(1,2)
        insereMesa(db, mesa1)

        val mesa2 = Mesas(2,4)
        insereMesa(db, mesa2)

        val refeicao1 = Refeicao("Almoço")
        insereRefeicao(db, refeicao1)

        val refeicao2 = Refeicao("Jantar")
        insereRefeicao(db, refeicao2)

        val reserva = Reservas(21062022, 5, cliente1.id , mesa1.id, refeicao1.id)
        insereReserva(db, reserva)

        reserva.data_reserva = 10072022
        reserva.numero_pessoas = 10
        reserva.clientes_id = cliente2.id
        reserva.mesas_id = mesa2.id
        reserva.refeicao_id = refeicao2.id


        val registosAlterados = TabelaBDReservas(db).update(
            reserva.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${reserva.id}"))

        assertEquals(1, registosAlterados)

        db.close()

    }



    @Test
    fun consegueEliminarCliente(){
        val db = getWritableDatabase()

        val cliente = Clientes("Teste", "123456789", "123456789", "Teste")
        insereCliente(db, cliente)


        val registosEliminados = TabelaBDClientes(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${cliente.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarMesa(){
        val db = getWritableDatabase()

        val mesa = Mesas(8, 10)
        insereMesa(db, mesa)


        val registosEliminados = TabelaBDMesas(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${mesa.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }


    @Test
    fun consegueEliminarRefeicao(){
        val db = getWritableDatabase()

        val refeicao = Refeicao("Almoço")
        insereRefeicao(db, refeicao)

        val registosEliminados = TabelaBDRefeicao(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${refeicao.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarReserva(){

        val db = getWritableDatabase()

        val cliente = Clientes("João", "924563987", "196348529", "Av. Dr. Francisco Sá Carneiro 50, 6300-559 Guarda")
        insereCliente(db, cliente)

        val mesa = Mesas(5, 4)
        insereMesa(db, mesa)

        val refeicao = Refeicao("Almoço")
        insereRefeicao(db, refeicao)

        val reserva = Reservas(21062022, 5, cliente.id , mesa.id, refeicao.id)
        insereReserva(db, reserva)

        val registosEliminados = TabelaBDReservas(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${refeicao.id}"))

        assertEquals(1, registosEliminados)

        db.close()

    }

    @Test
    fun consegueLerClientes(){
        val db = getWritableDatabase()

        val cliente = Clientes("João", "924563987", "196348529", "Av. Dr. Francisco Sá Carneiro 50, 6300-559 Guarda")
        insereCliente(db, cliente)

        val cursor = TabelaBDClientes(db).query(
            TabelaBDClientes.TODOS_CAMPOS_CIENTES,
            "${BaseColumns._ID}=?",
            arrayOf("${cliente.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val clienteBD = Clientes.fromCursor(cursor)

        assertEquals(cliente, clienteBD)

    }


    @Test
    fun consegueLerMesas(){
        val db = getWritableDatabase()

        val mesa = Mesas(8, 10)
        insereMesa(db, mesa)

        val cursor = TabelaBDMesas(db).query(
            TabelaBDMesas.TODOS_CAMPOS_MESAS,
            "${BaseColumns._ID}=?",
            arrayOf("${mesa.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val mesaBD = Mesas.fromCursor(cursor)

        assertEquals(mesa, mesaBD)

    }


    @Test
    fun consegueLerRefeicao(){
        val db = getWritableDatabase()

        val refeicao = Refeicao("Almoço")
        insereRefeicao(db, refeicao)

        val cursor = TabelaBDRefeicao(db).query(
            TabelaBDRefeicao.TODOS_CAMPOS_REFEICAO,
            "${BaseColumns._ID}=?",
            arrayOf("${refeicao.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val refeicaoBD = Refeicao.fromCursor(cursor)

        assertEquals(refeicao, refeicaoBD)

    }


    @Test
    fun consegueLerReserva(){
        val db = getWritableDatabase()


        val cliente = Clientes("João", "924563987", "196348529", "Av. Dr. Francisco Sá Carneiro 50, 6300-559 Guarda")
        insereCliente(db, cliente)

        val mesa = Mesas(5, 4)
        insereMesa(db, mesa)

        val refeicao = Refeicao("Almoço")
        insereRefeicao(db, refeicao)

        val reserva = Reservas(21062022, 5, cliente.id , mesa.id, refeicao.id)
        insereReserva(db, reserva)


        val cursor = TabelaBDReservas(db).query(
            TabelaBDReservas.TODOS_CAMPOS_RESERVA,
            "${TabelaBDReservas.CAMPO_ID}=?",
            arrayOf("${reserva.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val reservaBD = Reservas.fromCursor(cursor)

        assertEquals(reserva, reservaBD)

        db.close()
    }

}