package com.example.zikaron.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.zikaron.R
import com.example.zikaron.models.Product

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(color = Color.White)
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(10.dp))
            // Image(painter = painterResource(id = R.drawable.zikaron), contentDescription = "si", modifier = Modifier.size(120.dp))
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.name,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.description,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$${product.price}",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Green
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun preview() {
    ProductCard(product = Product(
    1,"Pulsera", "fefsfsdfsds", 20, "https://i.ibb.co/WpBtK3d/collar-placa.jpg"
    ))
}