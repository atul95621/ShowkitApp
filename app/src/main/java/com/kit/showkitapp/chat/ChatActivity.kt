package com.kit.showkitapp.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kit.showkitapp.R
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        openFragment(ContactChatFragment())

        tvCalls.setOnClickListener()
        {
            tvCalls.setTextColor(ContextCompat.getColor(this, R.color.black))
            tvContacts.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
            tvSoc.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))

            viewCall.visibility = View.VISIBLE
            viewContact.visibility = View.GONE
            viewSocial.visibility = View.GONE

            openFragment(CallsFragment())
        }
        tvContacts.setOnClickListener()
        {
            tvCalls.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
            tvContacts.setTextColor(ContextCompat.getColor(this, R.color.black))
            tvSoc.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))

            viewCall.visibility = View.GONE
            viewContact.visibility = View.VISIBLE
            viewSocial.visibility = View.GONE
            openFragment(ContactChatFragment())
        }
        tvSoc.setOnClickListener()
        {
            tvCalls.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
            tvContacts.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
            tvSoc.setTextColor(ContextCompat.getColor(this, R.color.black))

            viewCall.visibility = View.GONE
            viewContact.visibility = View.GONE
            viewSocial.visibility = View.VISIBLE
        }


        linFAB.setOnClickListener()
        {
            var intent = Intent(this, NewCallActivity::class.java)
            startActivity(intent)
        }
    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }
}