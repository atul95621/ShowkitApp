package com.mindorks.retrofit.coroutines.data.api

import com.kit.showkitapp.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("update_user_profile")
    suspend fun updateUserProfile(
        @Header("Authorization") auth: RequestBody,
        @Part("user_fullname") user_fullname: RequestBody,
        @Part("showkt_id") showkt_id: RequestBody,
        @Part("user_gender") user_gender: RequestBody,
        @Part("user_dob") user_dob: RequestBody,
        @Part("is_hide_dob") is_hide_dob: RequestBody,
        @Part("device_token") device_token: RequestBody,
        @Part("device_id") device_id: RequestBody,
        @Part("device_type") device_type: RequestBody,
        @Part("from_mobile") from_mobile: RequestBody,
        @Part("user_curent_location") user_curent_location: RequestBody,
        @Part file: MultipartBody.Part?
    ): SignUpModel


    @FormUrlEncoded
    @POST("send_otp")
    suspend fun sendOTP(
        @Field("mobile") mobile: String,
        @Field("country_code") country_code: String,
        @Field("from_mobile") from_mobile: String,
    ): ContinueMobDataModel

    @FormUrlEncoded
    @POST("otp_verify")
    suspend fun verifyOTP(
        @Field("mobile") mobile: String,
        @Field("otp") otp: String,
        @Field("device_id") device_id: String,
        @Field("device_type") device_type: String,
        @Field("device_token") device_token: String,
        @Field("user_curent_location") user_curent_location: String,
        @Field("from_mobile") from_mobile: String
    ): VerifyOtpDataModel


    @FormUrlEncoded
    @POST("social_login")
    suspend fun socialLoginApi(
        @Field("email") email: String,
        @Field("login_type") login_type: String,
        @Field("device_id") device_id: String,
        @Field("device_type") device_type: String,
        @Field("device_token") device_token: String,
        @Field("from_mobile") from_mobile: String

    ): SocialLoginModel

    @FormUrlEncoded
    @POST("get_language")
    suspend fun getLanguageApi(
        @Field("cat_id") cat_id: String
    ): LanguageModel

    @FormUrlEncoded
    @POST("get_category")
    suspend fun getCategoryApi(
        @Field("cat_id") cat_id: String
    ): CategoryIntrestModel

    @FormUrlEncoded
    @POST("add_update_passcode")
    suspend fun addPasscode(
        @Header("Authorization") auth: String,
        @Field("passcode") passcode: String

    ): SetPasscodeModel

    @FormUrlEncoded
    @POST("check_showktid_exist")
    suspend fun validateID(
        @Field("showkt_id") showkt_id: String
    ): ValidateIDModel
}