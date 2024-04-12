package com.sun.gmelody.data.repository.core

import java.lang.Exception

interface OnResultListener<T> {
    fun onSuccess(data: T)

    fun onError(exception: Exception?)
}
