package com.example.reservarestaurantes

import android.content.ContentValues

data class Mesas (var id: Long, var numero_mesa: Int, var quantidade_lugares: Int) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDMesas.CAMPO_NUMERO_MESA, numero_mesa)
        valores.put(TabelaBDMesas.CAMPO_QUANTIDADE_LUGARES, quantidade_lugares)

        return valores
    }
}