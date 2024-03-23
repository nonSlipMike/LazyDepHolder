package com.example.network_impl.querystabs

import android.content.Context
import okhttp3.Request

class Mocker(
    private val context: Context,
    //private val standGetter: () -> String
) : MockProvider {

   // private var config: MockConfig = MockConfig(context, standGetter)
   private var config: MockConfig = MockConfig(context)


    /**
     * Тут можно добавлять все необходимые моки
     */
    @MockerMarker
    fun mockerConfig(config: MockConfig.() -> Unit) {
       // this.config = MockConfig(context, standGetter)
        this.config = MockConfig(context)
        this.config.apply(config)
    }

    override fun getMock(request: Request): MockData? = config.getMock(request)
}
