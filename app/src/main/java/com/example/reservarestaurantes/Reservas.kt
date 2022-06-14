package com.example.reservarestaurantes

import android.content.ContentValues
import java.util.*

data class Reservas (var id:Long , var data_reserva:Int, var numero_pessoas:Int, var clientes_id:Long, var mesas_id:Long, var refeicao_id:Long) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDReservas.CAMPO_DATARESERVA, data_reserva)
        valores.put(TabelaBDReservas.CAMPO_NRPESSOAS, numero_pessoas)
        valores.put(TabelaBDReservas.CAMPO_CLIENTES_ID, clientes_id)
        valores.put(TabelaBDReservas.CAMPO_MESAS_ID, mesas_id)
        valores.put(TabelaBDReservas.CAMPO_REFEICAO_ID, refeicao_id)

        return valores
    }

}