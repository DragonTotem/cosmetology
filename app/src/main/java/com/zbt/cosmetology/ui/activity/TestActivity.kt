package com.zbt.cosmetology.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Trace
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.tencent.bugly.crashreport.CrashReport
import kotlinx.android.synthetic.main.activity_test.*
import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseActivity
import com.zbt.cosmetology.app.ext.showMessage
import com.zbt.cosmetology.databinding.ActivityTestBinding
import com.zbt.cosmetology.ui.adapter.TestAdapter
import com.zbt.cosmetology.viewmodel.request.RequestLoginRegisterViewModel
import com.zbt.common.ext.parseState
import com.zbt.common.ext.util.logd
import com.zbt.cosmetology.app.network.ApiService
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

/**
 * @author : hgj
 * @date   : 2020/8/26
 */
const val URL = "https://publicobject.com/helloworld.txt"

@Route(path = "ss", group = "ss", name = "test", priority = 101)
class TestActivity : BaseActivity<BaseViewModel, ActivityTestBinding>() {


    val viewModel: RequestLoginRegisterViewModel by viewModels()

    val adapter: TestAdapter by lazy { TestAdapter(arrayListOf()) }

    override fun layoutId() = R.layout.activity_test


    override fun initView(savedInstanceState: Bundle?) {
        Trace.beginSection("TestActivity")

        //强烈注意：使用addLoadingObserve 将非绑定当前activity的viewmodel绑定loading回调 防止出现请求时不显示 loading 弹窗bug
        addLoadingObserve(viewModel)

        adapter.run {
            clickAction = { position, item, state ->
                "海王收到了点击事件，并准备发一个红包".logd()
            }
        }
        button.setOnClickListener {
//            viewModel.loginReq("123", "123456")
            val intent = Intent()
            intent.setClassName("com.zbt.test", "com.zbt.test.MainService")
            startService(intent)
            startActivityForResult(Intent(this, TestActivity::class.java), 1003)
        }

        resources.assets

        val retrofit = Retrofit.Builder()
            .baseUrl("https://mockapi.eolinker.com/9IiwI82f58c23411240ed608ceca204b2f185014507cbe3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        val call: Call<ResponseBody> = service.getUserData()
        call.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }

        })
        for(i in 1..1000){
            println()
        }
        Trace.endSection()
    }

    override fun createObserver() {
        //测试在activity中 loading弹窗是否正确
        viewModel.loginResult.observe(this, Observer {
            parseState(it, {
                showMessage(it.nickname)
            }, {
                showMessage(it.errorMsg)
            })
        })

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun testOkHttp() {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(java.time.Duration.ofSeconds(10))
            .readTimeout(java.time.Duration.ofSeconds(10))
            .build()

        val request: Request = Request.Builder()
            .url(URL).build()

        val call = okHttpClient.newCall(request)
        val response = call.execute()

    }


}

