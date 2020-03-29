package com.test.testcoolrocket.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.test.testcoolrocket.R
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

class ViewSaver {
    companion object {
        fun saveViesAsImage(view: View, activity: Activity) {
            val bitmap =
                Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            if (ContextCompat
                    .checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    0
                )
            } else {
                writeFile(bitmap, activity)
            }
        }

        private fun writeFile(bitmap: Bitmap, context: Context) {
            val path =
                ("${Environment.getExternalStorageDirectory()}/Pictures/screenshot-${System.currentTimeMillis()}.png")
            val file = File(path)
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            Timber.i(file.toString())
            if (file.exists()) file.delete()
            try {
                val out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                out.flush()
                out.close()
                context.getString(R.string.activity_main_save_text).also {
                    context.toast(it)
                    Timber.i(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.e("ViewSaver writeFile ${e.localizedMessage}")
            }
        }
    }
}