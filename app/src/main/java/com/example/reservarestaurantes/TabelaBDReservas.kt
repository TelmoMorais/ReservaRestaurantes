package com.example.reservarestaurantes

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDReservas (db: SQLiteDatabase) :TabelaBD(db, NOMETABELA)  {
    override fun cria(){
        db.execSQL("CREATE TABLE $nome(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_DATARESERVA DATE NOT NULL, " +
                " $CAMPO_NRPESSOAS INTEGER NOT NULL, " +
                "$CAMPO_CLIENTES_ID INTEGER NOT NULL," +
                " $CAMPO_MESAS_ID INTEGER NOT NULL,  " +
                "$CAMPO_REFEICAO_ID INTEGER NOT NULL, " +
                "FOREIGN KEY ($CAMPO_CLIENTES_ID) REFERENCES ${TabelaBDClientes.NOMETABELA}(${BaseColumns._ID})" +
                "FOREIGN KEY ($CAMPO_MESAS_ID) REFERENCES ${TabelaBDMesas.NOMETABELA}(${BaseColumns._ID}), " +
                "FOREIGN KEY ($CAMPO_REFEICAO_ID) REFERENCES ${TabelaBDRefeicao.NOMETABELA}(${BaseColumns._ID}) ON DELETE RESTRICT ) ")
    }

    companion object{
        const val NOMETABELA = "Reservas"
        const val CAMPO_DATARESERVA = "Data_Reserva"
        const val CAMPO_NRPESSOAS = "Numero_Pessoas"
        const val CAMPO_CLIENTES_ID = "Clientes_Id"
        const val CAMPO_MESAS_ID = "Mesas_Id"
        const val CAMPO_REFEICAO_ID = "Refeicao_Id"

    }
}