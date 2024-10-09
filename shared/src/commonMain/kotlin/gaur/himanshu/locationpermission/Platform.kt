package gaur.himanshu.locationpermission

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform