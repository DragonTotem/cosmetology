package com.zbt.cosmetology.ui.fragment.login

import android.os.Bundle
import android.widget.CompoundButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.include_toolbar.*
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseFragment
import com.zbt.cosmetology.app.ext.initClose
import com.zbt.cosmetology.app.ext.showMessage
import com.zbt.cosmetology.app.util.CacheUtil
import com.zbt.cosmetology.app.util.SettingUtil
import com.zbt.cosmetology.databinding.FragmentRegisterBinding
import com.zbt.cosmetology.viewmodel.request.RequestLoginRegisterViewModel
import com.zbt.cosmetology.viewmodel.state.LoginRegisterViewModel
import com.zbt.common.ext.nav
import com.zbt.common.ext.navigateAction
import com.zbt.common.ext.parseState

/**
 * Author　: zbt
 * Time　: 2019/12/24
 * Description　:
 */
class RegisterFrgment : BaseFragment<LoginRegisterViewModel, FragmentRegisterBinding>() {

    private val requestLoginRegisterViewModel:RequestLoginRegisterViewModel by viewModels()

    override fun layoutId() = R.layout.fragment_register

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.viewmodel = mViewModel
        mDatabind.click = ProxyClick()
        toolbar.initClose("注册") {
            nav().navigateUp()
        }
        //设置颜色跟主题颜色一致
        appViewModel.appColor.value?.let {
            SettingUtil.setShapColor(registerSub, it)
            toolbar.setBackgroundColor(it)
        }
    }

    override fun createObserver() {
        requestLoginRegisterViewModel.loginResult.observe(
            viewLifecycleOwner,
            Observer { resultState ->
                parseState(resultState, {
                    CacheUtil.setIsLogin(true)
                    CacheUtil.setUser(it)
                    appViewModel.userinfo.value = it
                    nav().navigateAction(R.id.action_registerFrgment_to_mainFragment)
                }, {
                    showMessage(it.errorMsg)
                })
            })
    }


    inner class ProxyClick {
        /**清空*/
        fun clear() {
            mViewModel.username.value=""
        }

        /**注册*/
        fun register() {
            when {
                mViewModel.username.value.isEmpty() -> showMessage("请填写账号")
                mViewModel.password.get().isEmpty() -> showMessage("请填写密码")
                mViewModel.password2.get().isEmpty() -> showMessage("请填写确认密码")
                mViewModel.password.get().length < 6 -> showMessage("密码最少6位")
                mViewModel.password.get() != mViewModel.password2.get() -> showMessage("密码不一致")
                else -> requestLoginRegisterViewModel.registerAndlogin(
                    mViewModel.username.value,
                    mViewModel.password.get()
                )
            }
        }

        var onCheckedChangeListener1 = CompoundButton.OnCheckedChangeListener { _, isChecked ->
            mViewModel.isShowPwd.set(isChecked)
        }
        var onCheckedChangeListener2 = CompoundButton.OnCheckedChangeListener { _, isChecked ->
            mViewModel.isShowPwd2.set(isChecked)
        }
    }
}