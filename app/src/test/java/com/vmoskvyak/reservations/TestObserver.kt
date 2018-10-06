package com.vmoskvyak.reservations

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

class TestObserver<T> : Observer<T> {

    val observedValues = mutableListOf<T?>()

    override fun onChanged(value: T?) {
        observedValues.add(value)
    }

}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}
