package com.zbt.common.network.interceptor.logging

import android.util.Log
import android.util.TimeUtils
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 *Author: zbt
 *Time: 2021/4/24 14:23
 *Description: This is HttpLogInterceptor
 */
class HttpLogInterceptor : Interceptor {
    private val tag = "HttpInfo"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.i(tag, "request method: ${request.method} Url: ${request.url}")
        val headers = request.headers
        for (i in 0 until headers.size) {
            Log.i(tag, "${headers.name(i)}: ${headers.value(i)}")
        }
        val body = request.body
        body?.run {
            Log.i(tag, "Content-Type: ${this.contentType()} Content-Length: ${this.contentLength()}")
            val buffer = okio.Buffer()
            this.writeTo(buffer)
            if (this is FormBody) {
                Log.i(tag, "request params form data: ${buffer.readByteString().utf8()}")
            } else {
                Log.i(tag, "request params json data: {${buffer.readByteString().utf8()}}")
            }
        }
        Log.i(tag, "End print info for request")

        val startNs = System.nanoTime()
        val response = chain.proceed(request)
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        Log.i(tag, "response code: ${response.code} Url: ${request.url}")
        Log.i(tag, "time: $tookMs")
        Log.i(tag, "response message: ${response.message}")
        val responseHeaders = response.headers
        for (i in 0 until responseHeaders.size) {
            Log.i(tag, "${responseHeaders.name(i)}: ${responseHeaders.value(i)}")
        }
        response.body?.run {
            val source = this.source()
            source.request(Long.MAX_VALUE)
            val sourceBuffer = source.buffer
            // 直接用response.string()会导致流关闭而报错！ java.lang.IllegalStateException: closed
            Log.i(tag, sourceBuffer.clone().readString(Charset.defaultCharset()))
            Log.i(tag, "byte size: ${sourceBuffer.size}")
        } ?: apply {
            Log.i(tag, "response no http info")
        }
        Log.i(tag, "End print info for response")
        return response
    }
}