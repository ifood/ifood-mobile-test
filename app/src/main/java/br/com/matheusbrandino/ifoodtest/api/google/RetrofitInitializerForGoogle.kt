package br.com.matheusbrandino.ifoodtest.api.google

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializerForGoogle {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    companion object {
        private const val URL = "https://language.googleapis.com/"
    }
}