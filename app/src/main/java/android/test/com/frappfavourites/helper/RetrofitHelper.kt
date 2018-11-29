package android.test.com.frappfavourites.helper

import android.test.com.frappfavourites.classes.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {
        fun create(): RetrofitApiService {
            val retrofit = Retrofit.Builder()
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL).build()

            return retrofit.create(RetrofitApiService::class.java)
        }

        private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor(getHttpLoggingInterceptor()).build()

        private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }
    }
}