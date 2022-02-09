package com.sun.mvp.screen

import com.sun.mvp.R
import com.sun.mvp.screen.listmovie.MoviesFragment
import com.sun.mvp.utils.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(MoviesFragment::javaClass.name)
            .replace(R.id.layoutContainer, MoviesFragment.newInstance())
            .commit()
    }

    override fun initData() {
    }
}
