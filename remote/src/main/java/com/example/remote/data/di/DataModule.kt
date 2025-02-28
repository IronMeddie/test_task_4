package com.example.remote.data.di

import android.content.Context
import android.content.pm.ApplicationInfo
import com.example.remote.data.remote.ApiService
import com.example.remote.data.remote.NetworkConnectionInterceptor
import com.example.remote.data.repository.ApiRepositoryImpl
import com.example.remote.data.repository.ApiRepository
import com.example.remote.data.utils.Constance
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    fun providesLoggingInterceptor(@ApplicationContext context: Context): HttpLoggingInterceptor {
        // check for debug or release
        return if (context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0) HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
        else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    fun providesNetworkConnectionInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }

    @Provides
    fun providesOkHttpClient(
        logging: HttpLoggingInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constance.TIME_OUT_CONNECTION, TimeUnit.SECONDS)
            .readTimeout(Constance.TIME_OUT_CONNECTION, TimeUnit.SECONDS)
            .writeTimeout(Constance.TIME_OUT_CONNECTION, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .addInterceptor(logging)
            .addInterceptor(networkConnectionInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductsRepository(api: ApiService): ApiRepository {
        return ApiRepositoryImpl(api)
    }


    @Provides
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    fun providesRetrofit(
        converterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constance.BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}