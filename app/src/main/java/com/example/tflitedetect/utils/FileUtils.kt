package com.xyberprince.tflitedetect.utils

import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Build
import android.os.Environment
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

/**
 * Stores a Bitmap image to external storage using a specified file name.
 * @param bm: The Bitmap image to be stored.
 * @param fileName: The name of the file to save the Bitmap.
 */
fun storeBitmap(bm: Bitmap, fileName: String?) {
    CoroutineScope(Dispatchers.IO).launch {
        // Define the directory path for saving the image
        val dir = Environment.getExternalStorageDirectory().toString() + "/TFLiteDetect/"

        // Create the folder if it doesn't exist
        val folder = File(dir)
        if (!folder.exists()) {
            folder.mkdir()
        }

        // Create the file and handle exceptions
        val file = File(folder, fileName)
        try {
            file.createNewFile()
        } catch (e: Exception) {
            // Handle the exception by using an alternative method
            storeAlternatively(bm, fileName)
        }

        // Write the Bitmap to the file
        try {
            val fOut = FileOutputStream(file)
            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut)
            fOut.flush()
            fOut.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

/**
 * Stores a Bitmap image to external storage using an alternative method.
 * @param bm: The Bitmap image to be stored.
 * @param fileName: The name of the file to save the Bitmap.
 */
fun storeAlternatively(bm: Bitmap, fileName: String?) {
    // Determine the directory based on Android version
    val dir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/TFLiteDetect/" + fileName)
    } else {
        File(Environment.getExternalStorageDirectory().toString() + "/TFLiteDetect/" + fileName)
    }

    // Create the directory if it doesn't exist
    if (!dir.exists()) dir.parentFile?.mkdirs()

    // Write the Bitmap to the file
    try {
        val fOut = FileOutputStream(dir)
        bm.compress(Bitmap.CompressFormat.PNG, 85, fOut)
        fOut.flush()
        fOut.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * Captures a screenshot of a specified view.
 * @param screenView: The view to capture as a screenshot.
 * @return A Bitmap image representing the screenshot.
 */
fun getScreenShot(screenView: View): Bitmap {
    screenView.isDrawingCacheEnabled = true
    screenView.buildDrawingCache(true)
    val bitmap = Bitmap.createBitmap(screenView.drawingCache)
    screenView.isDrawingCacheEnabled = false
    return bitmap
}

/**
 * Rotates a Bitmap image by a specified number of degrees.
 * @param degrees: The number of degrees to rotate the Bitmap image.
 * @return The rotated Bitmap image.
 */
fun Bitmap.rotate(degrees: Float): Bitmap {
    val matrix = Matrix().apply {
        postRotate(degrees)
    }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}
