package gaur.himanshu.locationpermission.permission

internal interface LocationPermission {

    fun isPermissionGranted(): Boolean

    fun requestLocationPermission(onPermissionResult: (Boolean) -> Unit)

}