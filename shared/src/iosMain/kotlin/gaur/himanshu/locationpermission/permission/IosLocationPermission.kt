package gaur.himanshu.locationpermission.permission

import platform.CoreLocation.CLAuthorizationStatus
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.CoreLocation.kCLAuthorizationStatusNotDetermined
import platform.darwin.NSObject

class IosLocationPermission : LocationPermission {

    private val locationManager = CLLocationManager()

    override fun isPermissionGranted(): Boolean {
        val status = CLLocationManager.authorizationStatus()
        return status == kCLAuthorizationStatusAuthorizedWhenInUse || status == kCLAuthorizationStatusAuthorizedAlways
    }

    override fun requestLocationPermission(onPermissionResult: (Boolean) -> Unit) {
        val status = CLLocationManager.authorizationStatus()
        when (status) {
            kCLAuthorizationStatusNotDetermined -> {
                val delegate = LocationPermissionDelegate(onPermissionResult)
                locationManager.delegate = delegate
                locationManager.requestWhenInUseAuthorization()
            }

            kCLAuthorizationStatusAuthorizedWhenInUse -> {
                onPermissionResult.invoke(true)
            }

            else -> onPermissionResult.invoke(false)
        }
    }
}

private class LocationPermissionDelegate(
    private val permissionCallback: (Boolean) -> Unit
) : NSObject(), CLLocationManagerDelegateProtocol {
    override fun locationManager(
        manager: CLLocationManager,
        didChangeAuthorizationStatus: CLAuthorizationStatus
    ) {
        when (didChangeAuthorizationStatus) {
            kCLAuthorizationStatusAuthorizedAlways, kCLAuthorizationStatusAuthorizedWhenInUse -> {
                permissionCallback.invoke(true)
            }

            else -> {
                permissionCallback.invoke(false)
            }
        }
    }
}