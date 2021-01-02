package com.kit.showkitapp.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kit.showkitapp.R
import com.kit.showkitapp.activity.SearchActivity
import com.kit.showkitapp.adapter.TopUserAdapter
import com.kit.showkitapp.adapter.VideosAdapter
import com.kit.showkitapp.model.TopModel


class TopFragment(var searchActivity: SearchActivity) : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var adapter: TopUserAdapter? = null
    internal lateinit var layoutManager: LinearLayoutManager
    var arrayList = ArrayList<TopModel>()


    private var recyclerViewVideo: RecyclerView? = null
    private var videosAdapter: VideosAdapter? = null
    internal lateinit var layoutManagerVideo: LinearLayoutManager
    var arrayListVideo = ArrayList<TopModel>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_top, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvTop) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.setNestedScrollingEnabled(false)
        layoutManager = LinearLayoutManager(searchActivity)
        recyclerView!!.setLayoutManager(layoutManager)


        recyclerViewVideo = view.findViewById(R.id.rvVideos) as RecyclerView
        recyclerViewVideo!!.setHasFixedSize(true)
        recyclerViewVideo!!.setNestedScrollingEnabled(false)
        layoutManagerVideo = LinearLayoutManager(searchActivity)
        recyclerViewVideo!!.setLayoutManager(
            GridLayoutManager(
                searchActivity, 2

            )
        )


        load();
    }


    fun load() {
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));


        adapter = TopUserAdapter(arrayList, searchActivity, this)
        recyclerView?.adapter = adapter


        videosAdapter = VideosAdapter(arrayList, searchActivity, this)
        recyclerViewVideo?.adapter = videosAdapter


        adapter?.notifyDataSetChanged();
        videosAdapter?.notifyDataSetChanged();

    }

}