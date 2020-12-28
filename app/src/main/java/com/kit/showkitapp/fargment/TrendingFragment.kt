package com.kit.showkitapp.fargment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kit.showkitapp.activity.HomeActivity
import com.kit.showkitapp.R
import com.kit.showkitapp.adapter.TrendingAdapter
import com.kit.showkitapp.model.TrendingModel

class TrendingFragment(var homeActivity: HomeActivity) : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var adapter: TrendingAdapter? = null
    internal lateinit var layoutManager: LinearLayoutManager
    var arrayList = ArrayList<TrendingModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeActivity.hideHeader()


        recyclerView = view.findViewById(R.id.rvTrending) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.setNestedScrollingEnabled(false)
        layoutManager = LinearLayoutManager(homeActivity)
        recyclerView!!.setLayoutManager(layoutManager)

        load();

    }

    // bottomsheetdailog
    fun opencloseBottomSheetDailog() {
      /*  MyBottomSheetDialogFragment().apply {
            show(childFragmentManager, tag)
        }*/

    }

    fun load() {
        arrayList.add(TrendingModel("iAmChirag"));
        arrayList.add(TrendingModel("iAmChirag"));
        arrayList.add(TrendingModel("iAmChirag"));
        arrayList.add(TrendingModel("iAmChirag"));
        arrayList.add(TrendingModel("iAmChirag"));

        adapter = TrendingAdapter(arrayList, homeActivity,this)
        recyclerView?.adapter = adapter

        adapter?.notifyDataSetChanged();
    }
}