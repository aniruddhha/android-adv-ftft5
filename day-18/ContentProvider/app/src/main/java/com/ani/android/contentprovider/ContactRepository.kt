package com.ani.android.contentprovider

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContactRepository(val context: Context) {

    private val cr: ContentResolver;

    private val PROJECTION = arrayOf(
        ContactsContract.Data.DISPLAY_NAME,
        ContactsContract.Data.DATA1,
    )

    init {
        cr = context.contentResolver
    }
    fun fetchContacts(): Flow<Contact> {
        //select (name, number) from contact

        return flow {
            cr.query(
                ContactsContract.Data.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null,
            )?.apply {

                val contact = Contact(
                    name = getString(0),
                    number = getString(1),
                )

                Log.i("@ani", contact.toString())
                this@flow.emit(contact)
                close()
            }
        }
    }

    fun fetchContactsList(): List<Contact>? {
        //select (name, number) from contact

            createVehicleClass()

            return cr.query(
                ContactsContract.Data.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null,
            )?.run {

                Log.i("@ani", "Cursor $this")
                val _list: MutableList<Contact> = mutableListOf()
                val list: List<Contact> = _list

                while (moveToNext()) {
                    val contact = Contact(
                        name = getString(0),
                        number = getString(1),
                    )

                    Log.i("@ani", contact.toString())
                    _list.add(contact)
                }
                close()

                list
            }
        }

    fun createVehicleClass() {

        val cv = ContentValues()
        cv.put("id", 12)
        cv.put("vehicleClass", "abc")
        cv.put("numAxel", 4)
        cv.put("fair", 100)

        cr.insert(
            Uri.parse("content://com.ani.provider.vehicle.data"),
            cv
        )
    }
}

