package com.example.reservarestaurantes

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Refeicao (var tipo_refeicao: String="", var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDRefeicao.CAMPO_TIPO_REFEICAO, tipo_refeicao)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Refeicao{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posRefeicao = cursor.getColumnIndex(TabelaBDRefeicao.CAMPO_TIPO_REFEICAO)

            val id = cursor.getLong(posId)
            val tipoRefeicao = cursor.getString(posRefeicao)



            return Refeicao(tipoRefeicao, id)
        }
    }
}