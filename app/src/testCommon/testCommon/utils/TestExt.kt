package testCommon.utils

import com.squareup.moshi.Types
import testCommon.di.moshi
import java.io.File

val JsonMockFile.json: String
    get() {
        val uri = this.javaClass.classLoader?.getResource("$path$fileName") ?: return ""
        val file = File(uri.path)
        return String(file.readBytes())
    }

inline fun <reified T> JsonMockFile.fromJsonList(): List<T> {
    val type = Types.newParameterizedType(List::class.java, T::class.java)
    return moshi.adapter<List<T>>(type).fromJson(this.json) ?: listOf()
}
