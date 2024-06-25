package com.ani.android.file

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {

    private lateinit var photoUri: Uri

    private val requestStoragePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if ((permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true) &&
            (permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true)
        ) {
            openCamera()
        }
    }

    private val requestImagePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        }
    }


    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { sts ->
        Log.i("@ani", "Camara Status $sts")

        if (sts) {
            writePublicImages()
        }
    }

    private fun openCamera() {

        val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL_PRIMARY
                )
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "my-photo-${Math.random() * 1000}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/Ani")
//                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }
        photoUri = contentResolver.insert( imageCollection, values)!!
//        contentResolver.insert(imageCollection, value )
        Log.i("@ani", "Photo URI $photoUri")
        takePictureLauncher.launch(photoUri)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(modifier = Modifier.fillMaxSize()) {

                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.READ_MEDIA_IMAGES
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    openCamera()
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        requestImagePermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                    }
                }
            }
        }
    }
    private fun readAllPublicImages() {

        // image, name and size

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE
        )
        val selection = null
        val selectionArgs = null
        val sortOrder = "${MediaStore.Images.Media.DISPLAY_NAME} ASC"

        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->

            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val size = cursor.getLong(sizeColumn)

                val imageUri =
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                Log.i("@ani", "Id $id, Name $name, Size $size")
                Log.i("@ani", "URI $imageUri")
            }
        }
    }

    private fun writePublicImages() {
//        val resolver = applicationContext.contentResolver
//
//        val newImageDetails = ContentValues().apply {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
////                put(MediaStore.Images.Media.IS_PENDING, 0)
//            }
//        }
//
//        resolver.update(photoUri, newImageDetails, null, null)
//        resolver.insert(photoUri, newImageDetails)

        readAllPublicImages()
    }

    private fun arePermissionsGranted(): Boolean {
//
//        Log.i("@ani", "Read Storage "+(ContextCompat.checkSelfPermission(
//            this,
//            Manifest.permission.READ_EXTERNAL_STORAGE
//        ) == PackageManager.PERMISSION_GRANTED))
//        Log.i("@ani", "Write Storage "+(ContextCompat.checkSelfPermission(
//            this,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//        ) == PackageManager.PERMISSION_GRANTED))

        return (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestNeededPermissions() = requestStoragePermissionLauncher.launch(
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    )

    private fun storeInternally() {
        val file = File(filesDir, "abc.txt")
        Log.i("@ani", "Internal Storage ${file.path}")

        val data = "Hey Hi How are you"

        openFileOutput("abc.txt", MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
    }

    private fun readInternally() {
        val str = openFileInput("abc.txt").bufferedReader().useLines { lines ->
            lines.fold("") { some, text ->
                "$some\n$text"
            }
        }

        Log.i("@ani", str)
    }

    private fun storePrivateExternally() {
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) return

        val storages = ContextCompat.getExternalFilesDirs(this, null)
        storages.forEach { Log.i("@ani", it.path) }

        val data = "Hey Hi, how are you ??"
        val file = File(storages[0], "abc.txt")

        FileOutputStream(file, true).use {
            it.write(data.toByteArray())
        }
    }

    private fun readPrivateExternally() {
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) return

        val storages = ContextCompat.getExternalFilesDirs(this, null)

        val file = File(storages[0], "abc.txt")

        val str = FileInputStream(file).bufferedReader().useLines { lines ->
            lines.fold("") { some, text ->
                "$some\n$text"
            }
        }
        Log.i("@ani", str)
    }
}

