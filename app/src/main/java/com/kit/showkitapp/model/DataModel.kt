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

data class SocialLoginModel(
    val access_token: String,
    val `data`: Data,
    val message: String,
    val status: Int
)

data class Data(
    val firstName: String,
    val is_mobile: Boolean,
    val lastName: String,
    val login_type: String,
    val photo: String
)


data class LanguageModel(
    val `data`: ArrayList<Data_Lang>,
    val status: Int
)

data class Data_Lang(
    val _id: String,
    val createdAt: String,
    val desc: Any,
    val name: String,
    val status: Boolean,
    val updatedAt: String,
    var isSelected:Boolean=false

)

data class CategoryIntrestModel(
    val `data`: ArrayList<Data_Category>,
    val status: Int
)

data class Data_Category(
    val _id: String,
    val createdAt: String,
    val description: Any,
    val name: String,
    val status: Boolean,
    val updatedAt: String,
    var isSelected:Boolean=false
)