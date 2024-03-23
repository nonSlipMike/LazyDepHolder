package com.example.network_impl.querystabs

import android.content.Context
import okhttp3.Request
import timber.log.Timber

typealias MockPredicate = (Request) -> Boolean

class MockConfig(
    private val context: Context,
   // private val standGetter: () -> String
) {

    private val mocks = mutableListOf<Pair<MockPredicate, MockCreator.() -> MockData?>>()

//    @MockerMarker
//    val stand: String
//        get() = standGetter.invoke()

    /**
     * Все моки, помещенные в этот блок будут отключены
     */
    @Suppress("UNUSED_PARAMETER")
    @MockerMarker
    fun disable(block: MockConfig.() -> Unit) {
    }

    /**
     * Мокает запрос по урле
     */
    @MockerMarker
    infix fun String.mock(block: MockCreator.() -> MockData) {
        this@MockConfig.mock {
            predicate { request ->
                val originalUrl = getUrlWithoutParams(request.url.toString())
                val originalScheme = request.url.scheme
                val alternateUrl = alternateScheme(originalScheme) + originalUrl.substring(originalScheme.length until originalUrl.length)
                originalUrl.equals(this@mock, true) || alternateUrl.equals(this@mock, true)
            }
            creator(block)
        }
    }

    private fun getUrlWithoutParams(url: String): String {
        return url.split("?")[0]
    }

    private fun alternateScheme(scheme: String): String = when (scheme) {
        "http" -> "https"
        "https" -> "http"
        else -> scheme
    }

    /**
     * Мокает запрос по определенному условию
     */
    @MockerMarker
    fun mock(block: MockBuilder.() -> Unit) {
        MockBuilder().apply(block).run {
            mocks.add(Pair(predicate, creator))
        }
    }

    internal fun getMock(request: Request): MockData? {
        //val creator = MockCreator(context, request)
        val creator = MockCreator(context, request)

        mocks.forEach {
            if (it.first.invoke(request)) {
                Timber.d("mocked request = $request")
                return it.second.invoke(creator)
            }
        }

        Timber.d("direct request = $request")
        return null
    }
}

class MockBuilder {

    internal lateinit var predicate: MockPredicate
    internal lateinit var creator: MockCreator.() -> MockData?

    /**
     * Условие, по которому будет замокан запрос
     */
    @MockerMarker
    fun predicate(block: MockPredicate) {
        predicate = block
    }

    /**
     * Создание самого мока
     */
    @MockerMarker
    fun creator(block: MockCreator.() -> MockData?) {
        creator = block
    }
}

/**
 * Мок ответа на запрос
 */
data class MockData(
    /**
     * Код ответа
     */
    val code: Int = 200,
    /**
     * Сообщение (обычно используется при ошибках)
     */
    val message: String = "",
    /**
     * Тело ответа
     */
    val body: String,
    /**
     * Время выполнения замоканного запроса
     */
    val delay: Long = 500
)

@DslMarker
annotation class MockerMarker
