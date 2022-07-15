package com.example.reservarestaurantes

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBDReservas (db: SQLiteDatabase) :TabelaBD(db, NOMETABELA)  {
    override fun cria(){
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_DATARESERVA INTEGER NOT NULL, " +
                " $CAMPO_NRPESSOAS INTEGER NOT NULL, " +
                "$CAMPO_CLIENTES_ID INTEGER NOT NULL," +
                " $CAMPO_MESAS_ID INTEGER NOT NULL,  " +
                "$CAMPO_REFEICAO_ID INTEGER NOT NULL, " +
                "FOREIGN KEY ($CAMPO_CLIENTES_ID) REFERENCES ${TabelaBDClientes.NOMETABELA}(${BaseColumns._ID})" +
                "FOREIGN KEY ($CAMPO_MESAS_ID) REFERENCES ${TabelaBDMesas.NOMETABELA}(${BaseColumns._ID}), " +
                "FOREIGN KEY ($CAMPO_REFEICAO_ID) REFERENCES ${TabelaBDRefeicao.NOMETABELA}(${BaseColumns._ID}) ON DELETE RESTRICT ) ")
    }

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = " $NOMETABELA INNER JOIN ${TabelaBDClientes.NOMETABELA} ON ${TabelaBDClientes.CAMPO_ID} = $CAMPO_CLIENTES_ID"
        queryBuilder.tables = " $NOMETABELA INNER JOIN ${TabelaBDMesas.NOMETABELA} ON ${TabelaBDMesas.CAMPO_ID} = $CAMPO_MESAS_ID"
        queryBuilder.tables = " $NOMETABELA INNER JOIN ${TabelaBDRefeicao.NOMETABELA} ON ${TabelaBDRefeicao.CAMPO_ID} = $CAMPO_REFEICAO_ID"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object{
        const val NOMETABELA = "Reservas"
        const val CAMPO_ID = "$NOMETABELA.${BaseColumns._ID}"
        const val CAMPO_DATARESERVA = "Data_Reserva"
        const val CAMPO_NRPESSOAS = "Numero_Pessoas"
        const val CAMPO_CLIENTES_ID = "Clientes_Id"
        const val CAMPO_MESAS_ID = "Mesas_Id"
        const val CAMPO_REFEICAO_ID = "Refeicao_Id"

        val TODOS_CAMPOS_RESERVA = arrayOf(CAMPO_ID, CAMPO_DATARESERVA, CAMPO_NRPESSOAS, CAMPO_CLIENTES_ID, CAMPO_MESAS_ID, CAMPO_REFEICAO_ID)

    }
}