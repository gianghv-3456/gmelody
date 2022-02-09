package com.sun.mvp.data.repository.source.remote

import java.lang.Exception

interface OnResultListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception?)
}
