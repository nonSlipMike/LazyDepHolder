package com.example.network_impl.querystabs

import okhttp3.Request

interface MockProvider {

    fun getMock(request: Request): MockData?
}
