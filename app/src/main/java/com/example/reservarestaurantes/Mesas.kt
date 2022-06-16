package com.example.reservarestaurantes

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Mesas (var numero_mesa: Int, var quantidade_lugares: Int, var id: Long = -1 ) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDMesas.CAMPO_NUMERO_MESA, numero_mesa)
        valores.put(TabelaBDMesas.CAMPO_QUANTIDADE_LUGARES, quantidade_lugares)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Mesas{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNrMesa = cursor.getColumnIndex(TabelaBDMesas.CAMPO_NUMERO_MESA)
            val posQantLugares = cursor.getColumnIndex(TabelaBDMesas.CAMPO_QUANTIDADE_LUGARES)

            val id = cursor.getLong(posId)
            val nrMesa = cursor.getInt(posNrMesa)
            val quantLugares = cursor.getInt(posQantLugares)


            return Mesas(nrMesa, quantLugares, id)
        }
    }
}