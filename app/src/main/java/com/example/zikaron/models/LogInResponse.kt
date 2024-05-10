package com.example.zikaron.models

import com.google.gson.annotations.SerializedName

data class LogInResponse (
    @SerializedName("data") val data: String,
    var status: String
)