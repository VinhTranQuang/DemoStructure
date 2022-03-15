package com.android.example.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.android.example.R
import com.android.example.data.repository.CallRepositoryImp
import com.android.example.data.repository.BuysRepositoryImp
import com.android.example.data.repository.SellRepositoryImp
import com.android.example.data.source.local.AppDatabase
import com.android.example.data.source.remote.RetrofitAPIService
import com.android.example.domain.repository.BuyRepository
import com.android.example.domain.repository.CallRepository
import com.android.example.domain.repository.SellRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    companion object {
        private const val FOR_BASE_API = "FOR_BASE_API"

    }

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient,
        @Named(FOR_BASE_API) baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                /* If there is Internet, get the cache that was stored 5 seconds ago.
                 * If the cache is older than 5 seconds, then discard it,
                 * and indicate an error in fetching the response.
                 * The 'max-age' attribute is responsible for this behavior.
                 */
                request = if (true) request.newBuilder() // make default to true till i figure out how to inject network status
                    .header("Cache-Control", "public, max-age=" + 5).build()
                /*If there is no Internet, get the cache that was stored 7 days ago.
                 * If the cache is older than 7 days, then discard it,
                 * and indicate an error in fetching the response.
                 * The 'max-stale' attribute is responsible for this behavior.
                 * The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                 */
                else request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }
    @Provides
    @Named(FOR_BASE_API)
    internal fun baseUrl(@ApplicationContext context: Context): String {
        return context.getString(R.string.server_domain)
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideIsNetworkAvailable(@ApplicationContext context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): RetrofitAPIService {
        return retrofit.create(RetrofitAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideCallRepository(
        retrofitService: RetrofitAPIService
    ): CallRepository {
        return CallRepositoryImp(retrofitService)
    }
    @Singleton
    @Provides
    fun provideBuyRepository(
        retrofitService: RetrofitAPIService
    ): BuyRepository {
        return BuysRepositoryImp(retrofitService)
    }

    @Singleton
    @Provides
    fun providePhotoRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitAPIService
    ): SellRepository {
        return SellRepositoryImp(appDatabase, retrofitService)
    }
}
