package com.example.network_impl.impl

import android.content.Context
import com.example.network_api.RetrofitProvider
import com.example.network_impl.querystabs.MockInterceptor
import com.example.network_impl.querystabs.Mocker
import com.example.network_impl.querystabs.configure
import com.squareup.moshi.Moshi
import com.wdullaer.materialdatetimepicker.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.GeneralSecurityException
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.net.ssl.HostnameVerifier

class RetrofitProviderImpl @Inject constructor(private val context: Context) :
	RetrofitProvider {
	private var retrofitInstance: Retrofit? = null
	override val retrofit: Retrofit
		get() {
			return if (retrofitInstance != null) {
				retrofitInstance!!
			} else {
				retrofitInstance = initRetrofit()
				retrofitInstance!!
			}
		}

	private fun initRetrofit(): Retrofit {
		return Retrofit.Builder()
			.baseUrl("http://www.gogasoft.gog")
			.addConverterFactory(MoshiConverterFactory.create(moshi()))
			.client(okHttpClient())
			.build()
	}

	private fun moshi(): Moshi {
		return Moshi.Builder().build()
	}

	private fun okHttpClient(): OkHttpClient {
		val trustManager = TrustManagerFactory.provideTrustManager()
		val builder = OkHttpClient.Builder()
			.sslSocketFactory(socketFactory(trustManager), trustManager)
			.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
			.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
			.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
		if (BuildConfig.DEBUG) {
			builder.apply {
				addInterceptor(LoggingInterceptor())
				addInterceptor(MockInterceptor(Mocker(context).apply {
					configure()
					hostnameVerifier(HostnameVerifier { _, _ -> true })
				}))
			}
		}
		if (!BuildConfig.DEBUG) {
			builder.certificatePinner(CertificatePinner.DEFAULT)
		}
		return builder.build()
	}

	private fun socketFactory(trustManager: X509TrustManager): SSLSocketFactory {
		try {
			val protocol = "SSL"
			val sslContext = SSLContext.getInstance(protocol)
			sslContext.init(null, arrayOf<TrustManager>(trustManager), SecureRandom())
			return sslContext.socketFactory
		} catch (e: GeneralSecurityException) {
			throw AssertionError()
		}
	}
}

private const val CONNECT_TIMEOUT = 10L
private const val READ_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 60L
