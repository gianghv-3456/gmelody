package com.sun.mvp.screen

import android.util.Log
import com.sun.mvp.R
import com.sun.mvp.data.model.Movie
import com.sun.mvp.data.model.MovieEntry
import com.sun.mvp.data.repository.source.remote.OnResultListener
import com.sun.mvp.data.repository.source.remote.fetchjson.GetJsonFromUrl
import com.sun.mvp.utils.Constant
import com.sun.mvp.utils.base.BaseActivity
import java.lang.Exception

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
    }

    override fun initData() {
        GetJsonFromUrl(
            Constant.BASE_URL + Constant.BASE_PAGE,
            MovieEntry.MOVIES,
            listener = object : OnResultListener<List<Movie>> {
                override fun onSuccess(data: List<Movie>) {
                    Log.d("QQQ", data.toString())
                }

                override fun onError(exception: Exception?) {
                    Log.d("QQQ", "Error")
                }
            })
    }
}
