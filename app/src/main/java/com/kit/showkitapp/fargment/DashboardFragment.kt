package com.kit.showkitapp.fargment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kit.showkitapp.HomeActivity
import com.kit.showkitapp.R
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

        view.tvForYou.setOnClickListener()
        {
            openFragmentChild(ForYouFragment(homeActivity))
        }
        view.rvTrending.setOnClickListener()
        {
            openFragmentChild(TrendingFragment(homeActivity))
        }
    }

    fun openFragmentChild(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout_dash, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }
}