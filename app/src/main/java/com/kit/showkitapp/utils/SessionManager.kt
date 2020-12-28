package com.legal.smart.util;
import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context?) {

    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    // shared prefernce for storing the name, email and phone of previous user
    var prevSharedPref: SharedPreferences? = null
    var prevEditor: SharedPreferences.Editor? = null


    init {

        sharedPreferences = context?.getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()

        /*     // shared prefernce for previous user
             prevSharedPref = context?.getSharedPreferences("PREV_PREF", Context.MODE_PRIVATE)
             prevEditor = prevSharedPref?.edit()*/

    }


    companion object {


        val FCM_TOKEN = "fc_token"
        val LOGGEDIN = "loggedin"

        val USER_ID = "user_id"
        val NAME = "name"
        val USER_IMAGE = "user_image"
        val TOKEN = "token"
        val REGISTER_STATUS = "register_status"   //1 OR 0
        val PHONE_NUMBER = "phone_number"
        val EMAIL = "email"
        val COUNTRY = "country"
        val ROLE = "role"
        val AGE_GROUP = "age_group"
        val DEVICE_ID = "device_id"

    }


    fun setValues(key: String, value: String?) {
        editor?.putString(key, value)
        editor?.commit()
    }

    fun getValue(key: String): String? {
        return sharedPreferences!!.getString(key, "")
    }


    fun clearValues() {
        editor?.clear()
        editor?.commit()
    }

/*
    fun clearCetainValue(key: String) {
        editor?.remove(key)
        editor?.apply()
    }

    // get Previous value  of previous user
    fun getPrevUserDetail(key: String): String? {
        return prevSharedPref!!.getString(key, "")
    }

    fun setPrevUserDetail(key: String, value: String?) {
        prevEditor?.putString(key, value)
        prevEditor?.commit()
    }*/

}