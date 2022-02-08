package com.sun.mvp.data.source.remote

import java.lang.Exception

interface OnResultListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception?)
}
