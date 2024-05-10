package com.example.zikaron.viewmodels

import API
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewmodel: ViewModel() {

    private val _products = MutableStateFlow<List<String>>(emptyList())
    var products = _products.asStateFlow()

    var isLoaded by mutableStateOf(false)

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            isLoaded = false
            withContext(Dispatchers.IO){
                val response = API().getProducts()
                _products.value = response ?: emptyList()
                products = _products
            }
        }
    }
}