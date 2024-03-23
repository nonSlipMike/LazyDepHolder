package com.example.network_impl.impl

import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS

/**
 * wrapper [HttpLoggingInterceptor]
 * which defaults to level [BODY]
 * with exception for multipart requests
 * can be using only debug
 */
class LoggingInterceptor : Interceptor {

    private val defaultLoggingInterceptor = HttpLoggingInterceptor().apply { level = BODY }

    private val headersInterceptor by lazy { HttpLoggingInterceptor().apply { level = HEADERS } }

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (isMultipart(chain.request())) {
            headersInterceptor.intercept(chain)
        } else {
            defaultLoggingInterceptor.intercept(chain)
        }
    }

    private fun isMultipart(request: Request): Boolean {
        return request.headers["Content-Disposition"] != null || request.body is MultipartBody
    }
}
