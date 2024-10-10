package gaur.himanshu.locationpermission.permission

interface LocationPermission {

    fun isPermissionGranted(): Boolean

    fun requestLocationPermission(granted: (Boolean) -> Unit)

}