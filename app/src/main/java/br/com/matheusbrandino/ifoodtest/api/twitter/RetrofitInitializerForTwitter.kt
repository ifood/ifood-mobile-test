package br.com.matheusbrandino.ifoodtest.api.twitter

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

class RetrofitInitializerForTwitter {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createClient())
        .build()


    companion object {
        private const val URL = "https://api.twitter.com/"

        private fun createClient(): OkHttpClient {
            val consumer = createConsumer()
            return OkHttpClient.Builder()
                .addInterceptor(SigningInterceptor(consumer))
                .build()
        }

        private fun createConsumer(): OkHttpOAuthConsumer {
            val consumer = OkHttpOAuthConsumer(
                "E50U56dpw7pc2FYcBIsJG85Q0",
                "vbVnoBWOjxKoBWWW7CceJV0uIx9oqdplHhfBqd1mfPqk3vs2JG"
            )
            consumer.setTokenWithSecret(
                "167646472-VHzBon8bLk7y8xJ6GnDBE5PCQdoaF689xagLtVrS",
                "z8FXcGrs4rrZKQ9kYtBVlKb3gPTRY36g9C3qGFtmrIxvk"
            )
            return consumer
        }
    }
}