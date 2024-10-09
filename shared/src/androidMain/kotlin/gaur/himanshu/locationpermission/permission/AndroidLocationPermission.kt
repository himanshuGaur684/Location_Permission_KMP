package gaur.himanshu.locationpermission.permission

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

class AndroidLocationPermission(
    private val context: Context,
    private val launcher: ActivityResultLauncher<String>? = null,
) : LocationPermission {

    override fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun requestLocationPermission(onPermissionResult: (Boolean) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            launcher?.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}