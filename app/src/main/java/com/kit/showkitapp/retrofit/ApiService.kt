package com.mindorks.retrofit.coroutines.data.api

import com.kit.showkitapp.model.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("update_user_profile")
    suspend fun updateUserProfile(
        @Field("Authorization") auth: String,
        @Field("user_fullname") user_fullname: String,
        @Field("showkt_id") showkt_id: String,
        @Field("user_gender") user_gender: String,
        @Field("user_dob") user_dob: String,
        @Field("is_hide_dob") is_hide_dob: String,
        @Field("device_token") device_token: String,
        @Field("device_id") device_id: String,
        @Field("device_type") device_type: String,
        @Field("from_mobile") from_mobile: String,
        @Field("user_curent_location") user_curent_location: String
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
        @Field("Authorization") auth: String,
        @Field("passcode") passcode: String

    ): SetPasscodeModel

    @FormUrlEncoded
    @POST("check_showktid_exist")
    suspend fun validateID(
        @Field("showkt_id") showkt_id: String
    ): ValidateIDModel
}