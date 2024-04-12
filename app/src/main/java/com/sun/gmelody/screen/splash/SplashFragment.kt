package com.sun.gmelody.screen.splash

import android.view.LayoutInflater
import com.sun.gmelody.R
import com.sun.gmelody.databinding.FragmentSplashBinding
import com.sun.gmelody.screen.tracklist.TracksFragment
import com.sun.gmelody.utils.base.BaseFragment
import com.sun.gmelody.utils.ext.addFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater)
    }

    override fun initData() {
        // Do-nothing
    }

    override fun initView() {
        addFragment(R.id.layoutContainer, TracksFragment.newInstance(), addToBackStack = true)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}
