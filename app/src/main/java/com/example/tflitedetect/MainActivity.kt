package com.xyberprince.tflitedetect

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.tflitedetect.databinding.ActivityMainBinding

private val PERMISSIONS_REQUIRED = arrayOf(
    Manifest.permission.CAMERA,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)
private val REQUEST_PERMISSION_CODE = 1

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Request permissions if not already granted
        requestPermissionsIfNecessary()

        // Initialize the layout
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Check if any permissions were denied
        if (grantResults.isNotEmpty() && grantResults.contains(PackageManager.PERMISSION_DENIED)) {
            showPermissionDeniedDialog()
        }
    }

    private fun requestPermissionsIfNecessary() {
        // Check if permissions are granted
        if (!arePermissionsGranted()) {
            requestRequiredPermissions()
        }
    }

    private fun arePermissionsGranted(): Boolean {
        // Check if all required permissions are granted
        return PERMISSIONS_REQUIRED.all {
            ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestRequiredPermissions() {
        // Request the required permissions
        ActivityCompat.requestPermissions(this, PERMISSIONS_REQUIRED, REQUEST_PERMISSION_CODE)
    }

    private fun showPermissionDeniedDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
            .setTitle("Notice")
            .setMessage("Please make sure to allow all the permissions.")
            .setPositiveButton("Ok") { dialogInterface, _ ->
                dialogInterface.dismiss()
                requestRequiredPermissions()
            }
            .show()
    }
}
