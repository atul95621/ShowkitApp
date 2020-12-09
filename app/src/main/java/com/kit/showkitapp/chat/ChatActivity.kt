package com.kit.showkitapp.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            openFragment(CallsFragment())
        }
        tvContacts.setOnClickListener()
        {
            openFragment(ContactChatFragment())
        }
    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }
}