package com.example.reservarestaurantes

import android.content.ContentValues

data class Refeicao (var tipo_refeicao: String, var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDRefeicao.CAMPO_TIPO_REFEICAO, tipo_refeicao)

        return valores
    }
}