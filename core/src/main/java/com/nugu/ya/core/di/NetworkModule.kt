package com.nugu.ya.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.ConnectionPool
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.net.CookieManager
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true // 정의되지 않은 키값 무시
        coerceInputValues = true // 타입 체크 느슨하게 설정
        encodeDefaults = true // 디폴트 값 설정
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val trustAllCerts = object : X509TrustManager {
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return emptyArray()
            }
        }

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, arrayOf(trustAllCerts), SecureRandom())

        val connectionPool = ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.SECONDS)

        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCerts)
            .hostnameVerifier { _, _ -> true }
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .connectionPool(connectionPool)
            .cookieJar(JavaNetCookieJar(CookieManager()))
            .build()
    }

    private const val MAX_IDLE_CONNECTIONS = 5  // Connection Pool
    private const val KEEP_ALIVE_DURATION = 60L // KeepAlive Time
    private const val CONNECT_TIMEOUT = 5L  // Connection Timeout
    private const val READ_TIMEOUT = 60L    // Read Timeout
    private const val WRITE_TIMEOUT = 60L   // Write Timeout
    private const val SOCKET_TIMEOUT = 60L  // Socket Timeout
}