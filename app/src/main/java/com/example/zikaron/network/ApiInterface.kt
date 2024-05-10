package com.example.retrofitdogs.network


import com.example.zikaron.models.LogInDataModel
import com.example.zikaron.models.LogInResponse
import com.example.zikaron.models.Product
import com.example.zikaron.models.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("products") //Peticion get y definir url relativa
    suspend fun products(): Response<ProductResponse> //nombre de funcion para la peticion y tipo de

    @POST("logIn")
    suspend fun logIn(@Body body: LogInDataModel?): Response<LogInResponse>

}