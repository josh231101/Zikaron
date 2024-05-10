package com.example.zikaron.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.zikaron.components.BarraSuperior
import com.example.zikaron.components.ProductCard
import com.example.zikaron.models.Product
import com.example.zikaron.viewmodels.ProductViewmodel
import com.google.gson.Gson

@Composable
fun ProductsView(navController: NavHostController) {
    BarraSuperior {
        padding ->
        val productsViewModel = ProductViewmodel()
        var products = productsViewModel.products.collectAsState()
        productsViewModel.fetchData()
        Column(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            LazyColumn(modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()) {
                items(products.value) {
                    item -> Column {
                        val product = Gson().fromJson(item, Product::class.java)
                        ProductCard(product)
                    }
                }
            }
        }
    }
}