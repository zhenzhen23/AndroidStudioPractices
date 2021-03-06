package com.example.jetpacktest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.jetpacktest.Repository
import com.example.jetpacktest.User

class MainViewModel(countReserved: Int) : ViewModel() {

    val counter: LiveData<Int>
        get() = _counter
    private val _counter = MutableLiveData<Int>()

    private val userIdLiveData = MutableLiveData<String>()
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData){ userId ->
        Repository.getUser(userId)
    }

    private val refreshLiveData = MutableLiveData<Any?>()
//    val refreshResult = Transformations.switchMap(refreshLiveData){
//        Repository.refresh()
//    }

    fun refresh() {
        refreshLiveData.value = refreshLiveData.value
    }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

    fun getUser(userId: String){
        userIdLiveData.value = userId
    }
}