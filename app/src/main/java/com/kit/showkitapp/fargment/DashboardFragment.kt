package com.kit.showkitapp.fargment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import com.kit.showkitapp.activity.HomeActivity
import com.kit.showkitapp.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class DashboardFragment(var homeActivity: HomeActivity) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openFragmentChild(TrendingFragment(homeActivity))

        viewTrending.visibility = View.VISIBLE

        view.tvForYou.setOnClickListener()
        {
            tvForYou.setTextColor(ContextCompat.getColor(homeActivity, R.color.black))
            tvTrending.setTextColor(ContextCompat.getColor(homeActivity, R.color.dark_grey))
            viewForYou.visibility = View.VISIBLE
            viewTrending.visibility = View.GONE
            openFragmentChild(ForYouFragment(homeActivity))
        }
        view.tvTrending.setOnClickListener()
        {
            tvTrending.setTextColor(ContextCompat.getColor(homeActivity, R.color.black))
            tvForYou.setTextColor(ContextCompat.getColor(homeActivity, R.color.dark_grey))
            viewForYou.visibility = View.GONE
            viewTrending.visibility = View.VISIBLE

            openFragmentChild(TrendingFragment(homeActivity))
        }

        view.imgLang.setOnClickListener()
        {
            showLanguageDialog()
        }

        view.imgInterest.setOnClickListener()
        {
            showInterestDialog()
        }
    }

    fun openFragmentChild(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout_dash, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showLanguageDialog() {
        val dialog = Dialog(homeActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dailog_language)
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        dialog.setCancelable(true)
        dialog.show()
    }

    fun showInterestDialog() {
        val dialog = Dialog(homeActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dailog_image_select)
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        dialog.setCancelable(true)
        dialog.show()
    }
}