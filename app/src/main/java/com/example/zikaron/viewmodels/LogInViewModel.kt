package com.example.zikaron.viewmodels

import API
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zikaron.models.LogInDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogInViewModel: ViewModel() {
    private val _isAuth = MutableStateFlow(false)
    var isAuth = _isAuth.asStateFlow()

    private val _failedLogin = MutableStateFlow(false)
    var failedLogin = _failedLogin.asStateFlow()

    var isLoaded by mutableStateOf(false)

    fun postLogIn(logInDataModel: LogInDataModel) {
        viewModelScope.launch {
            isLoaded = false
            withContext(Dispatchers.IO){
                val response = API().postLogIn(logInDataModel.email, logInDataModel.password)
                _isAuth.value = response ?: false
                isAuth = _isAuth
                if (!isAuth.value) {
                    _failedLogin.value = true
                    failedLogin = _failedLogin
                }
            }
        }
    }
}