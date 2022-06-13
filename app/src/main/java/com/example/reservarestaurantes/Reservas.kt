package com.example.reservarestaurantes

import java.util.*

data class Reservas (var id:Long , var data_reserva:Date, var numero_pessoas:Long, var clientes_id:Long, var mesas_id: Long, var refeicao_id: Long) {
}