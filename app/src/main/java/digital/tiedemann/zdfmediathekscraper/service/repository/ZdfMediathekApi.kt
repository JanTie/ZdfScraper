package digital.tiedemann.zdfmediathekscraper.service.repository

import digital.tiedemann.zdfmediathekscraper.service.model.StartPageResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface ZdfMediathekApi {

    @GET("mediathekV2/start-page")
    fun loadStartPage(): Call<StartPageResponse>

    companion object {
        private const val BASE_URL = "https://zdf-cdn.live.cellular.de/"
        fun create() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create<ZdfMediathekApi>()
    }
}