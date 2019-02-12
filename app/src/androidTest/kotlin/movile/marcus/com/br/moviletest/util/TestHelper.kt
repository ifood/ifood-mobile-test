package movile.marcus.com.br.moviletest.util

import android.content.Context
import java.io.InputStream

class TestHelper {

    companion object {
        @Throws(Exception::class)
        private fun convertStreamToString(`is`: InputStream): String {
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            return String(buffer)
        }

        @Throws(Exception::class)
        fun getStringFromFile(context: Context, filePath: String): String {
            val stream = context.resources.assets.open(filePath)
            val ret = convertStreamToString(stream)
            stream.close()
            return ret
        }
    }

}