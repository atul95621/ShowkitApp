package com.kit.showkitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kit.showkitapp.fargment.TrendingFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        openFragment(TrendingFragment(this))
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
}