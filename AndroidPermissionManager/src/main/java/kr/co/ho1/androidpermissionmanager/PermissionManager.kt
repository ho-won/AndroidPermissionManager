package kr.co.ho1.androidpermissionmanager

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PermissionManager(var activity: Activity, var requiredPermissions: Array<String>, var optionalPermissions: Array<String>, var onGranted: (() -> Unit), var onDenied: (() -> Unit)) {
    var isRequired = true
    var position = -1

    init {
        GlobalScope.launch {
            requestPermission()
        }
    }

    private fun requestPermission() {
        position++
        if (isRequired && position < requiredPermissions.size) {
            if (ContextCompat.checkSelfPermission(activity, requiredPermissions[position]) == PackageManager.PERMISSION_GRANTED) {
                requestPermission()
            } else {
                ActivityCompat.requestPermissions(activity, arrayOf(requiredPermissions[position]), 0)
            }
        } else {
            if (isRequired) {
                position = 0
            }
            isRequired = false
            if (position < optionalPermissions.size) {
                if (ContextCompat.checkSelfPermission(activity, optionalPermissions[position]) == PackageManager.PERMISSION_GRANTED) {
                    requestPermission()
                } else {
                    ActivityCompat.requestPermissions(activity, arrayOf(optionalPermissions[position]), 0)
                }
            } else {
                onGranted()
            }
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (permissions.isNotEmpty() && grantResults.isNotEmpty()) {
            if (isRequired && position < requiredPermissions.size) {
                if (grantResults[0] == 0 && permissions[0] == requiredPermissions[position]) {
                    requestPermission()
                } else {
                    onDenied()
                }
            } else {
                requestPermission()
            }
        }
    }

}