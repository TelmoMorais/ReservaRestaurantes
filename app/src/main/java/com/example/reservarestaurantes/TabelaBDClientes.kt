package com.example.reservarestaurantes

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDClientes (db: SQLiteDatabase) :TabelaBD(db, NOMETABELA) {
    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME TEXT NOT NULL, $CAMPO_TELEFONE TEXT NOT NULL, $CAMPO_NIF TEXT NOT NULL, $CAMPO_MORADA TEXT NOT NULL)")
    }

    companion object{
        const val NOMETABELA = "Clientes"
        const val CAMPO_ID = "$NOMETABELA.${BaseColumns._ID}"
        const val CAMPO_NOME = "Nome"
        const val CAMPO_TELEFONE = "Contato_Telefonico"
        const val CAMPO_NIF = "NIF"
        const val CAMPO_MORADA = "Morada"

        val TODOS_CAMPOS_CIENTES = arrayOf(CAMPO_ID,CAMPO_NOME,CAMPO_TELEFONE,CAMPO_NIF,CAMPO_MORADA)

    }
}