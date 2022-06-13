package com.example.reservarestaurantes

import android.content.ContentValues

data class Clientes (var id:Long, var nome:String, var contato_telefonico:String, var nif:String, var morada: String) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDClientes.CAMPO_NOME, nome)
        valores.put(TabelaBDClientes.CAMPO_TELEFONE, contato_telefonico)
        valores.put(TabelaBDClientes.CAMPO_NIF, nif)
        valores.put(TabelaBDClientes.CAMPO_MORADA, morada)

        return valores
    }

}