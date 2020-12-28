package com.legal.smart.util


class Constants {
    companion object {

        //required for FCM
        const val CHANNEL_ID = "my_channel_legalsmart"
        const val CHANNEL_NAME = "LEGALSMART"
        const val CHANNEL_DESCRIPTION = "legalsmart"

        var ERROR_OCCURED = "OOps! Error Occured."
        var EXCEPTION_OCCURED = "OOps! Exception Occured."



        var INNER_LAW_ID = ""
        var LAW_ID = ""


        var locationOptions = HashSet<String>()
        var accessOptions = HashSet<String>()
        var areaOfInterestOptions = HashSet<String>()
        var courtPrefOptions = HashSet<String>()


        var SCREEN = ""

        var PROFILE_SCREEN_FROM = ""   //0- FROM normal usr  1- from attorney
        var conversionRate:Int=0


    }
}