package testCommon.di

import com.squareup.moshi.Moshi
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val baseUrl: String = "http://localhost:8080/"

val moshi: Moshi = Moshi.Builder().build()

fun converterFactory(moshi: Moshi): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

fun retrofit(
    baseUrl: String,
    converter: Converter.Factory
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(converter)
    .build()

val retrofit = retrofit(baseUrl, converterFactory(moshi))