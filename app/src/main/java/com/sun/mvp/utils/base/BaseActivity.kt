package com.sun.mvp.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        initView()
    }

    abstract fun getLayoutResourceId(): Int
    abstract fun initView()
}
