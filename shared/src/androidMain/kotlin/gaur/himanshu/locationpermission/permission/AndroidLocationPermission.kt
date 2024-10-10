package gaur.himanshu.locationpermission.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

class AndroidLocationPermission(
    private val context: Context,
    private val activityLauncher: ActivityResultLauncher<String>
) : LocationPermission {

    override fun isPermissionGranted(): Boolean {
      return  ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun requestLocationPermission(granted: (Boolean) -> Unit) {
        activityLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}