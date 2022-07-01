package com.example.reservarestaurantes

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDRefeicao (db: SQLiteDatabase) :TabelaBD(db, NOMETABELA) {

    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_TIPO_REFEICAO TEXT NOT NULL)")
    }

    companion object{
        const val NOMETABELA = "Refeicao"
        const val CAMPO_ID = "$NOMETABELA.${BaseColumns._ID}"
        const val CAMPO_TIPO_REFEICAO = "Tipo_Refeicao"

        val TODOS_CAMPOS_REFEICAO = arrayOf(CAMPO_ID, CAMPO_TIPO_REFEICAO)

    }
}