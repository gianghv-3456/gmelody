package com.sun.gmelody.screen

import com.sun.gmelody.R
import com.sun.gmelody.screen.splash.SplashFragment
import com.sun.gmelody.utils.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        supportFragmentManager.beginTransaction().addToBackStack(SplashFragment::javaClass.name)
            .replace(R.id.layoutContainer, SplashFragment.newInstance()).commit()

    }
}
