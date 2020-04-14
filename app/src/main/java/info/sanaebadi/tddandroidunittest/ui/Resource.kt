package info.sanaebadi.tddandroidunittest.ui

class Resource<T>(
    val status: Status,
    val data: T?,
    message: String
) {
    val message: String?

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    override fun equals(obj: Any?): Boolean {
        if (obj!!.javaClass != javaClass || obj.javaClass != Resource::class.java) {
            return false
        }
        val resource: Resource<T>? =
            obj as Resource<*>?
        if (resource!!.status != status) {
            return false
        }
        if (data != null) {
            if (resource.data !== data) {
                return false
            }
        }
        if (resource.message != null) {
            if (message == null) {
                return false
            }
            if (resource.message != message) {
                return false
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun <T> success(
            data: T,
            message: String
        ): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                message
            )
        }

        @JvmStatic
        fun <T> error(
            data: T?,
            msg: String
        ): Resource<T?> {
            return Resource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T?): Resource<T?> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }

    init {
        this.message = message
    }
}