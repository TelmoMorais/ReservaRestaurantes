package com.example.reservarestaurantes

import android.content.ContentValues

data class Refeicao (var id: Long, var tipo_refeicao: String) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDRefeicao.CAMPO_TIPO_REFEICAO, tipo_refeicao)

        return valores
    }
}