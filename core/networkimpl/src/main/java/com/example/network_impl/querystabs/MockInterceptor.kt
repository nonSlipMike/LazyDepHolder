package com.example.network_impl.querystabs

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import timber.log.Timber

internal class MockInterceptor(
	private val mockProvider: MockProvider
) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()

		val mock = mockProvider.getMock(request) ?: return proceedTransparently(chain)

		if (mock.delay > 0) {
			try {
				Thread.sleep(mock.delay)
			} catch (e: InterruptedException) {
				Timber.d(e.toString())
			}
		}
		ResponseBody.Companion.apply {
			return Response.Builder()
				.code(mock.code)
				.message(mock.message)
				.request(chain.request())
				.protocol(Protocol.HTTP_1_0)
				.body(mock.body.toResponseBody("application/json".toMediaTypeOrNull()))
				.addHeader("content-type", "application/json")
				.build()
		}
	}

	private fun proceedTransparently(chain: Interceptor.Chain) = chain.proceed(chain.request())
}
