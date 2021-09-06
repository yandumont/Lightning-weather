package com.example.lightning_weather.util



import com.example.lightning_weather.features.home.api.recyclerView.Weather
import com.example.lightning_weather.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface WeatherService {
    @GET("data/2.5/weather?")
    suspend fun getCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
//        @Query("q") city: String,
//        @Query("appid") app_id: String
    ) : Weather
}

object WeatherApi{
    val retrofitService : WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }
}

interface WeatherForecastService{
    @GET("data/2.5/forecast?")
    suspend fun getWeatherForecast(@Query("lat") lat: Double,
                                   @Query("lon") lon: Double,
                                   @Query("appid") appid: String) : WeatherForecast
}

object WeatherForecastApi{
    val weatherForecastService : WeatherForecastService by lazy {
        retrofit.create(WeatherForecastService::class.java)
    }
}

interface DailyForecastService {
    @GET("data/2.5/onecall?")
    suspend fun getDailyForecast(@Query("lat") lat: Double,
                                 @Query("lon") lon: Double,
                                 @Query("appid") appid: String) : DailyForecast
}

object DailyForecastApi{
    val dailyForecastService : DailyForecastService by lazy {
        retrofit.create(DailyForecastService::class.java)
    }
}
