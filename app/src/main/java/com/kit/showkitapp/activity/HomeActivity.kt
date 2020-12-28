package com.kit.showkitapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kit.showkitapp.R
import com.kit.showkitapp.chat.ChatActivity
import com.kit.showkitapp.fargment.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        window.statusBarColor = ContextCompat.getColor(this, R.color.white)


        openFragment(DashboardFragment(this))

        linProfile.setOnClickListener()
        {
            hideHeader()
            openFragment(ProfileEditFragment(this))

        }

        linChat.setOnClickListener()
        {
            hideHeader()
            var intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        linSearch.setOnClickListener()
        {
            showHeader()
            openFragment(SettingFragment(this))
        }

        linHome.setOnClickListener()
        {
            hideHeader()
            openFragment(DashboardFragment(this))
        }
    }

    fun openBottomSheet() {
        MyBottomSheetDialogFragment(this).apply {
            show(supportFragmentManager, MyBottomSheetDialogFragment.TAG)
        }
    }

    fun closeBottomSheet() {
        MyBottomSheetDialogFragment(this).apply {
            dismiss()
        }
    }

    fun openReplyBottomSheet() {
        ReplyBottomSheet(this).apply {
            show(supportFragmentManager, ReplyBottomSheet.TAG)
        }
    }

    fun closeReplyBottomSheet() {
        ReplyBottomSheet(this).apply {
            dismiss()
        }
    }

    fun setTitleName(title: String) {
        tvTitle.setText(title)
    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val fragments = supportFragmentManager.fragments
        if (fragments != null) {
            for (f in fragments) {
                /* if (f is TicketFragment) {
                     f.onActivityResult(requestCode, resultCode, data)
                 }*/
            }
        }

    }

    fun hideHeader() {
        frameHeader.visibility = View.GONE
    }

    fun showHeader() {
        frameHeader.visibility = View.VISIBLE
    }
}