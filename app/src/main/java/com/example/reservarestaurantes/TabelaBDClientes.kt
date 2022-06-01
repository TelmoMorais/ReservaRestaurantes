package com.example.reservarestaurantes

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDClientes (val db: SQLiteDatabase) {
    fun cria(){
        db.execSQL("CREATE TABLE $NOMETABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME TEXT NOT NULL, $CAMPO_TELEFONE TEXT NOT NULL, $CAMPO_NIF TEXT NOT NULL, $CAMPO_MORADA TEXT NOT NULL)")
    }

    companion object{
        const val NOMETABELA = "Clientes"
        const val CAMPO_NOME = "Nome"
        const val CAMPO_TELEFONE = "Contato_Telefonico"
        const val CAMPO_NIF = "NIF"
        const val CAMPO_MORADA = "Morada"

    }
}