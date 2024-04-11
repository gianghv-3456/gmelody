package com.sun.gmelody.screen.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.gmelody.R
import com.sun.gmelody.databinding.FragmentSplashBinding
import com.sun.gmelody.utils.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater)
    }

    override fun initData() {
    }

    override fun initView() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}
