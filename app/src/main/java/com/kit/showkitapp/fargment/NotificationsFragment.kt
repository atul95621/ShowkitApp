package com.kit.showkitapp.fargment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.kit.showkitapp.HomeActivity
import com.kit.showkitapp.R
import kotlinx.android.synthetic.main.fragment_notifications.view.*

class NotificationsFragment(var homeActivity: HomeActivity) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeActivity.setTitleName("Notifications")

        view.switchNotify.setOnClickListener()
        {
            showDialog("You won't recieve push notification but you will be able to see new notification when you open Showkit")

        }
    }


    private fun showDialog(title: String) {
        val dialog = Dialog(homeActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dailog_layout)

        dialog.show()

    }

}