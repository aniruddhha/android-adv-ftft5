package com.ani.android.data

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppDataProvider : ContentProvider() {

    private lateinit var db: AppDatabase
    private lateinit var dao: VehicleClassDao
    private val scp: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        Log.i("@ani", "${uri.toString()}")
        values?.apply {
            val vehCls = VehicleClass(
                id = values.getAsLong("id"),
                vehicleClass = values.getAsString("vehicleClass"),
                numAxel = values.getAsInteger("numAxel"),
                fair = values.getAsDouble("fair")
            )

            scp.launch {
                dao.createNewVehicleClass(vehCls)
            }
            return ContentUris.withAppendedId(uri, values.getAsLong("id"))
        }
        return null
    }

    override fun onCreate(): Boolean {

        Log.i("@ani", "---- Content Provider Loaded ---")
        db = (context as MyApp).db
        dao = db.getVehicleClassDao()

        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        TODO("Implement this to handle query requests from clients.")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}