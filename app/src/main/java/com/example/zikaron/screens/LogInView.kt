package com.example.zikaron.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.zikaron.components.BarraSuperior
import com.example.zikaron.models.LogInDataModel
import com.example.zikaron.viewmodels.LogInViewModel
import com.example.zikaron.viewmodels.PreferencesViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginView(navHostController: NavHostController) {
    var context = LocalContext.current
    val preferencesViewModel = PreferencesViewModel(context)
    var corrutineScope = rememberCoroutineScope()

    var email by remember {
        mutableStateOf("")
    }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    var logInViewModel = LogInViewModel()
    var isAuth = logInViewModel.isAuth.collectAsState()

    var failedLogin = logInViewModel.failedLogin.collectAsState()
    if (isAuth.value) {
        // Authenticate user
        corrutineScope.launch {
            preferencesViewModel.setUserAuthenticated("Prueba", true)
        }
        navHostController.navigate("Profile")
    }
    if (failedLogin.value) {
        Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
    }
    BarraSuperior {
        innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Inicio de sesión")
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier
                .border(width = 2.dp, shape = RoundedCornerShape(40.dp), color = Color(0xFFE7DFD1))
                .background(Color(0xFFE7DFD1))
                .padding(20.dp),
            ) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Correo electrónico: ")
                    TextField(value = email, onValueChange = {
                        email = it
                    })
                    Text(text = "Contraseña: ")
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        placeholder = { Text("Contraseña") },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    )
                    Button(
                        onClick = {
                                  if (email != "" && password != "") {
                                    logInViewModel.postLogIn(LogInDataModel(email, password))
                                  }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF908B8B)
                        )
                    ) {
                        Text(text = "Iniciar Sesión")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}