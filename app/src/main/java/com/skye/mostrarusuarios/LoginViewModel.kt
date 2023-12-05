package com.skye.mostrarusuarios
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel :ViewModel(){
    val loginResult = MutableLiveData<Boolean>()

    fun loginUser(username: String, password: String, users: List<User>) {
        val user = users.find { it.username == username && it.password == password }
        loginResult.value = user != null
    }
}