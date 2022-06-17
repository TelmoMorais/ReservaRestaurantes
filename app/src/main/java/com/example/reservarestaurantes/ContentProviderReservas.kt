package com.example.reservarestaurantes

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class ContentProviderReservas : ContentProvider() {

    var db : BDReservasRestauranteOpenHelper? = null

    override fun onCreate(): Boolean {
        db = BDReservasRestauranteOpenHelper(context)

        return true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    companion object{
        const val AUTHORITY = "com.example.reservarestaurantes"

        const val URI_CLIENTES = 100
        const val URI_CLIENTE_ESPECIFICO = 101
        const val URI_MESAS = 200
        const val URI_MESA_ESPECIFICA = 201
        const val URI_REFEICOES = 300
        const val URI_REFEICAO_ESPECIFICA = 301
        const val URI_RESERVAS = 400
        const val URI_RESERVA_ESPECIFICA = 401


        fun getUriMatcher() : UriMatcher {
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, TabelaBDClientes.NOMETABELA, URI_CLIENTES)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDClientes.NOMETABELA}/#", URI_CLIENTE_ESPECIFICO)
            uriMatcher.addURI(AUTHORITY, TabelaBDMesas.NOMETABELA, URI_MESAS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDMesas.NOMETABELA}/#", URI_MESA_ESPECIFICA)
            uriMatcher.addURI(AUTHORITY, TabelaBDRefeicao.NOMETABELA, URI_REFEICOES)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDRefeicao.NOMETABELA}/#", URI_REFEICAO_ESPECIFICA)
            uriMatcher.addURI(AUTHORITY, TabelaBDReservas.NOMETABELA, URI_RESERVAS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDReservas.NOMETABELA}/#", URI_RESERVA_ESPECIFICA)

            return uriMatcher
        }
    }

}