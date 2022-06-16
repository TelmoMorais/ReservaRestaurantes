package com.example.reservarestaurantes

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Clientes (var nome:String, var contato_telefonico:String, var nif:String, var morada: String, var id:Long = -1) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDClientes.CAMPO_NOME, nome)
        valores.put(TabelaBDClientes.CAMPO_TELEFONE, contato_telefonico)
        valores.put(TabelaBDClientes.CAMPO_NIF, nif)
        valores.put(TabelaBDClientes.CAMPO_MORADA, morada)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Clientes{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDClientes.CAMPO_NOME)
            val posTelefone = cursor.getColumnIndex(TabelaBDClientes.CAMPO_TELEFONE)
            val posNif = cursor.getColumnIndex(TabelaBDClientes.CAMPO_NIF)
            val posMorada = cursor.getColumnIndex(TabelaBDClientes.CAMPO_MORADA)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val telefone = cursor.getString(posTelefone)
            val nif = cursor.getString(posNif)
            val morada = cursor.getString(posMorada)

            return Clientes(nome, telefone,nif, morada, id)
        }
    }

}