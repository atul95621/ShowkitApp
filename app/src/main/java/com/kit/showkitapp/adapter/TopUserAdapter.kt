package com.kit.showkitapp.adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kit.showkitapp.R
import com.kit.showkitapp.activity.SearchActivity
import com.kit.showkitapp.fargment.TopFragment
import com.kit.showkitapp.model.TopModel
import kotlinx.android.synthetic.main.item_layout_top.view.*


class TopUserAdapter(
    var arrayList: ArrayList<TopModel>,
    var searchActivity: SearchActivity,
    var topFragment: TopFragment
) : RecyclerView.Adapter<TopUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopUserAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_top, parent, false)

        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: TopUserAdapter.ViewHolder, position: Int) {
        holder.bind(position, arrayList[position])
    }


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arrayList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, arrayList: TopModel) {
            itemView.name.setText(arrayList.name)


        }

    }
}