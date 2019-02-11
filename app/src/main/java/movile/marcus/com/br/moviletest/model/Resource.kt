package movile.marcus.com.br.moviletest.model


enum class Status { SUCCESS, DEFAULT_ERROR, INTERNET_ERROR, UNAUTHORIZED }

data class Resource<out T>(
    val exception: Throwable?,
    val status: Status,
    val data: T?
) {
    companion object {
        fun <T> success(data: T?) = Resource(
            null,
            Status.SUCCESS,
            data
        )

        fun error(exception: Throwable?, status: Status = Status.DEFAULT_ERROR) =
            Resource(exception, status, null)
    }
}