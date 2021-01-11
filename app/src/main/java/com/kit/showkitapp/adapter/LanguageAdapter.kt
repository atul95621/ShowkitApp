package com.kit.showkitapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kit.showkitapp.R
import com.kit.showkitapp.model.Data_Lang
import kotlinx.android.synthetic.main.item_layout_category.view.*
import kotlinx.android.synthetic.main.item_layout_language.view.*


class LanguageAdapter(
    var arrayListLang: ArrayList<Data_Lang>,
    var activity: Activity
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
        holder.bind(position, arrayListLang[position])
    }


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arrayListLang.size
    }


    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, arrayList: Data_Lang) {

            itemView.titleLang.setText(arrayList.name)

            if (arrayList.isSelected == false) {
//                itemView.titleLang.setTextColor(ContextCompat.getColor(activity,R.color.black))
                itemView.imgTick.setBackgroundResource(R.drawable.ic_oval)
            } else {
//                itemView.titleLang.setTextColor(ContextCompat.getColor(activity,R.color.white))
                itemView.imgTick.setBackgroundResource(R.drawable.ic_check)
            }

            itemView.img_flag.load(arrayList.full_urlfile)
            {
                placeholder(
                    R.drawable
                        .ic_india_flag
                )
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

        fun arrSize(): Int {
            return arrayListLang.size
        }

        fun turnAllNeutral() {
            for (i in 0 until arrayListLang.size) {
                arrayListLang[i].isSelected = false
            }
        }

    }
}