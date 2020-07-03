package com.example.android.gdgfinder.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://developers.google.com/community/gdg/groups/"
interface GdgApiService {
    @GET("directory.json")
    suspend fun getChapters():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            GdgResponse
}
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

object GdgApi {
    val retrofitService : GdgApiService by lazy { retrofit.create(GdgApiService::class.java) }
}
