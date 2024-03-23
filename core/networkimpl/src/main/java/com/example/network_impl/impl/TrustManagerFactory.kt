package com.example.network_impl.impl

import com.example.network_impl.BuildConfig
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.Arrays
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object TrustManagerFactory {

    fun provideTrustManager(): X509TrustManager {
        @Suppress("ConstantConditionIf")
        return if (BuildConfig.DEBUG) {
            newUnsafeTrustManager()
        } else {
            systemDefaultTrustManager()
        }
    }

    private fun systemDefaultTrustManager(): X509TrustManager {
        try {
            val trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm()
            )
            trustManagerFactory.init(null as KeyStore?)
            val trustManagers = trustManagerFactory.trustManagers
            if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
                throw IllegalStateException(
                    "Unexpected default trust managers:" + Arrays.toString(
                        trustManagers
                    )
                )
            }
            return trustManagers[0] as X509TrustManager
        } catch (e: GeneralSecurityException) {
            throw AssertionError() // The system has no TLS. Just give up.
        }
    }

    private fun newUnsafeTrustManager(): X509TrustManager {
        return object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }
}
