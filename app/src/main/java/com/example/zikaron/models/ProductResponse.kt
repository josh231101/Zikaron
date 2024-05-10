package com.example.zikaron.models

import com.google.gson.annotations.SerializedName

data class ProductResponse (
    @SerializedName("data") val data: List<String>,
    var status: String
)