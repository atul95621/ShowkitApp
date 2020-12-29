package com.kit.showkitapp.adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kit.showkitapp.activity.HomeActivity
import com.kit.showkitapp.R
import com.kit.showkitapp.fargment.DashboardFragment
import com.kit.showkitapp.fargment.TrendingFragment
import com.kit.showkitapp.model.Data_Category
import com.kit.showkitapp.model.Data_Lang
import com.kit.showkitapp.model.LanguageModel
import kotlinx.android.synthetic.main.item_layout_category.view.*


class CategoryAdapter(
    var arrayList: ArrayList<Data_Category>,
    var homeActivity: HomeActivity,
    var dashboardFragment: DashboardFragment
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_category, parent, false)

        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bind(position, arrayList[position])
    }


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arrayList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, arrayList: Data_Category) {

            itemView.title.setText(arrayList.name)

            if (arrayList.isSelected == false) {
                itemView.imgTickMark.visibility = View.GONE
            } else {
                itemView.imgTickMark.visibility = View.VISIBLE
            }

            itemView.linCategoryLayout.setOnClickListener()
            {
                if (arrayList.isSelected == false) {
                    arrayList.isSelected = true
                    notifyDataSetChanged()
                } else {
                    arrayList.isSelected = false
                    notifyDataSetChanged()

                }
            }


        }

    }
}