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
        val status = locationManager.authorizationStatus()
        return status == kCLAuthorizationStatusAuthorizedAlways || status == kCLAuthorizationStatusAuthorizedWhenInUse
    }

    override fun requestLocationPermission(granted: (Boolean) -> Unit) {
        val status = locationManager.authorizationStatus()
        when (status) {
            kCLAuthorizationStatusNotDetermined -> {
                locationManager.delegate = LocationManagerDelegate(granted)
                locationManager.requestWhenInUseAuthorization()
             }

            else -> {
                granted.invoke(true)
            }
        }
    }
}

private class LocationManagerDelegate(private val granted: (Boolean) -> Unit) : NSObject(),
    CLLocationManagerDelegateProtocol {

    override fun locationManager(
        manager: CLLocationManager,
        didChangeAuthorizationStatus: CLAuthorizationStatus
    ) {
       when(didChangeAuthorizationStatus){
           kCLAuthorizationStatusAuthorizedAlways, kCLAuthorizationStatusAuthorizedWhenInUse->{
               granted.invoke(true)
           }
           else->{
               granted.invoke(false)
           }
       }
    }


}









