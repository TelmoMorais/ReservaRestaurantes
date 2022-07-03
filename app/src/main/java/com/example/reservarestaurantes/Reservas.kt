package com.example.reservarestaurantes

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Reservas(var data_reserva:Long, var numero_pessoas:Int, var clientes_id: Long, var mesas_id: Long, var refeicao_id: Long, var id:Long = -1): Serializable {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDReservas.CAMPO_DATARESERVA, data_reserva)
        valores.put(TabelaBDReservas.CAMPO_NRPESSOAS, numero_pessoas)
        valores.put(TabelaBDReservas.CAMPO_CLIENTES_ID, clientes_id)
        valores.put(TabelaBDReservas.CAMPO_MESAS_ID, mesas_id)
        valores.put(TabelaBDReservas.CAMPO_REFEICAO_ID, refeicao_id)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Reservas{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posDataRes = cursor.getColumnIndex(TabelaBDReservas.CAMPO_DATARESERVA)
            val posNrPess = cursor.getColumnIndex(TabelaBDReservas.CAMPO_NRPESSOAS)
            val posClienteId = cursor.getColumnIndex(TabelaBDReservas.CAMPO_CLIENTES_ID)
            val posMesaId = cursor.getColumnIndex(TabelaBDReservas.CAMPO_MESAS_ID)
            val posRefeicaoId = cursor.getColumnIndex(TabelaBDReservas.CAMPO_REFEICAO_ID)

            val id= cursor.getLong(posId)
            val dataRes = cursor.getLong(posDataRes)
            val nrPess = cursor.getInt(posNrPess)
            val clienteId = cursor.getLong(posClienteId)
            val mesaId = cursor.getLong(posMesaId)
            val refeicaoId = cursor.getLong(posRefeicaoId)

            return Reservas(dataRes, nrPess,clienteId,mesaId,refeicaoId, id)

        }
    }

}