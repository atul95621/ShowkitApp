package com.kit.showkitapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kit.showkitapp.R
import com.kit.showkitapp.activity.SearchActivity
import com.kit.showkitapp.fargment.TopFragment
import com.kit.showkitapp.fargment.UsersFragment
import com.kit.showkitapp.model.TopModel
import kotlinx.android.synthetic.main.item_layout_top.view.*


class UsersTabAdapter(
    var arrayList: ArrayList<TopModel>,
    var searchActivity: SearchActivity,
    var usersFragment: UsersFragment
) : RecyclerView.Adapter<UsersTabAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersTabAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_top, parent, false)

        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: UsersTabAdapter.ViewHolder, position: Int) {
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