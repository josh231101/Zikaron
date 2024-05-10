package com.example.zikaron.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.zikaron.R
import com.example.zikaron.components.BarraSuperior
import com.example.zikaron.viewmodels.PreferencesViewModel

@Composable
fun HomeView(navController: NavHostController) {
    val context = LocalContext.current
    val preferencesViewModel = PreferencesViewModel(context)
    var isAuth = preferencesViewModel.isAuth.collectAsState(initial = false)
    var name = preferencesViewModel.name.collectAsState(initial = "")
    BarraSuperior {
            innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = "Background",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Bienvenido", fontSize = 25.sp)
                Text(text = "a Zikaron Jewelery App", fontSize = 25.sp)
                Image(painter = painterResource(id = R.drawable.zikaron), contentDescription = "logo", modifier = Modifier.size(200.dp))
                Text(text = "Inicia sesión para ver el tiempo de vida de tu producto", fontSize = 25.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.size(40.dp))
                if (isAuth.value) {
                    Button(onClick = {
                        navController.navigate("Profile")
                    }) {
                        Text(text = "Ir al perfil")
                    }
                }
                else {
                    Button(onClick = {
                        navController.navigate("LogIn")
                    }) {
                        Text(text = "Iniciar sesión")
                    }
                }

                Button(onClick = {
                    navController.navigate("Products")
                }) {
                    Text(text = "Tienda")
                }
                Spacer(modifier = Modifier.size(40.dp))
                Text(text = "O visítanos en", fontSize = 20.sp, textAlign = TextAlign.Center)
                Text(text = "zikaronjewelery.com", fontSize = 16.sp, textAlign = TextAlign.Center)

            }
        }
    }


}

@Composable
@Preview(showBackground = true)
fun previewww() {
    HomeView(navController = rememberNavController())
}
