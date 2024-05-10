package com.example.zikaron.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.zikaron.components.BarraSuperior
import com.example.zikaron.viewmodels.PreferencesViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileView(navHostController: NavHostController) {
    val context = LocalContext.current
    val preferencesViewModel = PreferencesViewModel(context = context)
    var corrutineScope = rememberCoroutineScope()
    BarraSuperior {
        padding ->
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Bienvenido a tu portal", fontSize = 32.sp)
            Text(text = "¡Aquí podrás consultar el estatus de tus pulseras!")
            Button(onClick = {
                corrutineScope.launch {
                    preferencesViewModel.setUserAuthenticated("", false)
                    navHostController.navigate("Home")
                }
            }) {
                Text(text = "¡Salir!")
            }
        }
    }
}