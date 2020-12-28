package com.kit.showkitapp.model


data class TrendingModel(
    var name: String
)

data class SignUpModel(
    val message: String,
    val status: Int
)

data class ContinueMobDataModel(
    val message: String,
    val status: Int
)

data class VerifyOtpDataModel(
    val message: String,
    val status: Int
)