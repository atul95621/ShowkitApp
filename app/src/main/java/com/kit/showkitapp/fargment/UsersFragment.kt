package com.kit.showkitapp.fargment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kit.showkitapp.R
import com.kit.showkitapp.activity.SearchActivity
import com.kit.showkitapp.adapter.UsersAdapter
import com.kit.showkitapp.adapter.UsersTabAdapter
import com.kit.showkitapp.adapter.VideosAdapter
import com.kit.showkitapp.model.TopModel


class UsersFragment(var searchActivity: SearchActivity) : Fragment() {
    private var recyclerViewVideo: RecyclerView? = null
    private var usersTabAdapter: UsersTabAdapter? = null
    internal lateinit var layoutManagerVideo: LinearLayoutManager
    var arrayList = ArrayList<TopModel>()


    var arrayListVideo = ArrayList<TopModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_users, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerViewVideo = view.findViewById(R.id.rvUsers) as RecyclerView
        recyclerViewVideo!!.setHasFixedSize(true)
        recyclerViewVideo!!.setNestedScrollingEnabled(false)
        layoutManagerVideo = LinearLayoutManager(searchActivity)
        recyclerViewVideo!!.setLayoutManager(
            layoutManagerVideo
        )



        load();
    }


    fun load() {
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));
        arrayList.add(TopModel("iamshilpa"));


        usersTabAdapter = UsersTabAdapter(arrayList, searchActivity, this)
        recyclerViewVideo?.adapter = usersTabAdapter


        usersTabAdapter?.notifyDataSetChanged();

    }

}