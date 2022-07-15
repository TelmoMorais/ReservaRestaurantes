package com.example.reservarestaurantes

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDMesas (db: SQLiteDatabase) :TabelaBD(db, NOMETABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NUMERO_MESA INTEGER NOT NULL, $CAMPO_QUANTIDADE_LUGARES INTEGER NOT NULL)")
    }

    companion object{
        const val NOMETABELA = "Mesas"
        const val CAMPO_ID = "$NOMETABELA.${BaseColumns._ID}"
        const val CAMPO_NUMERO_MESA = "Numero_Mesa"
        const val CAMPO_QUANTIDADE_LUGARES = "Quantidade_Lugares"

        val TODOS_CAMPOS_MESAS = arrayOf(CAMPO_ID, CAMPO_NUMERO_MESA, CAMPO_QUANTIDADE_LUGARES)
    }

}