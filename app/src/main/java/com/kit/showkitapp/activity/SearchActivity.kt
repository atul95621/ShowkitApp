package com.kit.showkitapp.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kit.showkitapp.R
import com.kit.showkitapp.fargment.TopFragment
import com.kit.showkitapp.fargment.UsersFragment
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_layout_language.view.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        tvUsers.setOnClickListener()
        {
            tvUsers.setTextColor(ContextCompat.getColor(this, R.color.black))
            tvTop.setTextColor(ContextCompat.getColor(this, R.color.addCustomerTextAlpha))
            viewTop.visibility = View.GONE
            viewUser.visibility = View.VISIBLE

            openFragment(UsersFragment(this))
        }

        tvTop.setOnClickListener()
        {
            tvTop.setTextColor(ContextCompat.getColor(this, R.color.black))
            tvUsers.setTextColor(ContextCompat.getColor(this, R.color.addCustomerTextAlpha))
            viewUser.visibility = View.GONE
            viewTop.visibility = View.VISIBLE

            openFragment(TopFragment(this))
        }


        openFragment(TopFragment(this))

    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.searchcontainer, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }
}