package bloder.com.domain.api_response

interface ResponseGod {

    val responseMap get() = mapOf<Int, () -> Any>(
            200 to { on200() },
            404 to { on404() }
    )

    fun on200()
    fun on404() { unknown(404) }
    fun unknown(code: Int) {}

    fun handle(code: Int) {
        if (responseMap[code] != null) responseMap[code]?.invoke()
        else unknown(code)
    }
}