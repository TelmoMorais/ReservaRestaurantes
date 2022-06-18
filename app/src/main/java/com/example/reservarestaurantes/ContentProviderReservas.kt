package com.example.reservarestaurantes

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class ContentProviderReservas : ContentProvider() {

    var dbOpenHelper : BDReservasRestauranteOpenHelper? = null

    override fun onCreate(): Boolean {
        dbOpenHelper = BDReservasRestauranteOpenHelper(context)

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbOpenHelper!!.readableDatabase

        requireNotNull(projection)
        val colunas = projection as Array<String>

        val argsSelecao = selectionArgs as Array<String>

        val id = uri.lastPathSegment

        val cursor = when (getUriMatcher().match(uri)) {
            URI_RESERVAS -> TabelaBDReservas(db).query(colunas, selection, argsSelecao,null, null, sortOrder)
            URI_CLIENTES -> TabelaBDClientes(db).query(colunas, selection, argsSelecao,null, null, sortOrder)
            URI_MESAS -> TabelaBDMesas(db).query(colunas, selection, argsSelecao,null, null, sortOrder)
            URI_REFEICOES -> TabelaBDRefeicao(db).query(colunas, selection, argsSelecao,null, null, sortOrder)
            URI_RESERVA_ESPECIFICA -> TabelaBDReservas(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_CLIENTE_ESPECIFICO -> TabelaBDClientes(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_MESA_ESPECIFICA -> TabelaBDMesas(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)
            URI_REFEICAO_ESPECIFICA -> TabelaBDRefeicao(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("${id}"), null, null, null)

            else -> null
        }

        db.close()

        return cursor
    }

    override fun getType(uri: Uri): String? =
        when (getUriMatcher().match(uri)) {
            URI_RESERVAS -> "$MULTIPLOS_REGISTOS/${TabelaBDReservas.NOMETABELA}"
            URI_CLIENTES -> "$MULTIPLOS_REGISTOS/${TabelaBDClientes.NOMETABELA}"
            URI_MESAS -> "$MULTIPLOS_REGISTOS/${TabelaBDMesas.NOMETABELA}"
            URI_REFEICOES -> "$MULTIPLOS_REGISTOS/${TabelaBDRefeicao.NOMETABELA}"
            URI_RESERVA_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDReservas.NOMETABELA}"
            URI_CLIENTE_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDClientes.NOMETABELA}"
            URI_MESA_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDMesas.NOMETABELA}"
            URI_REFEICAO_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDRefeicao.NOMETABELA}"
            else -> null
        }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbOpenHelper!!.writableDatabase

        requireNotNull(values)

        val id = when (getUriMatcher().match(uri)) {
            URI_RESERVAS -> TabelaBDReservas(db).insert(values)
            URI_CLIENTES -> TabelaBDClientes(db).insert(values)
            URI_MESAS -> TabelaBDMesas(db).insert(values)
            URI_REFEICOES -> TabelaBDRefeicao(db).insert(values)
            else -> -1
        }

        db.close()

        if (id == -1L) return null

        return Uri.withAppendedPath(uri, "$id")
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


        const val UNICO_REGISTO = "vnd.android.cursor.item"
        const val MULTIPLOS_REGISTOS = "vnd.android.cursor.dir"


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