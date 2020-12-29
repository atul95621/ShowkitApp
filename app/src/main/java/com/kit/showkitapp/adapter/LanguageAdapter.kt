package com.kit.showkitapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kit.showkitapp.activity.HomeActivity
import com.kit.showkitapp.R
import com.kit.showkitapp.fargment.DashboardFragment
import com.kit.showkitapp.fargment.TrendingFragment
import com.kit.showkitapp.model.Data_Lang
import com.kit.showkitapp.model.LanguageModel
import kotlinx.android.synthetic.main.item_layout_category.view.*
import kotlinx.android.synthetic.main.item_layout_language.view.*


class LanguageAdapter(
    var arrayList: ArrayList<Data_Lang>,
    var homeActivity: HomeActivity,
    var dashboardFragment: DashboardFragment
) : RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_language, parent, false)

        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: LanguageAdapter.ViewHolder, position: Int) {
        holder.bind(position, arrayList[position])
    }


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arrayList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, arrayList: Data_Lang) {

            itemView.titleLang.setText(arrayList.name)

            if (arrayList.isSelected == false) {
                itemView.titleLang.setTextColor(ContextCompat.getColor(homeActivity,R.color.black))
                itemView.linLanguageLayout.setBackgroundResource(R.drawable.shadow_curve)
            } else {
                itemView.titleLang.setTextColor(ContextCompat.getColor(homeActivity,R.color.white))
                itemView.linLanguageLayout.setBackgroundResource(R.drawable.grad_lang)
            }


            itemView.linLanguageLayout.setOnClickListener()
            {
                if (arrayList.isSelected == false) {
                    turnAllNeutral()
                    arrayList.isSelected = true
                    notifyDataSetChanged()
                } else {
                    turnAllNeutral()
                    arrayList.isSelected = false
                    notifyDataSetChanged()

                }
            }


        }

        fun turnAllNeutral() {
            for (i in 0 until arrayList.size) {
                arrayList[i].isSelected = false
            }
        }

    }
}