package com.kit.showkitapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kit.showkitapp.R
import com.kit.showkitapp.model.Data_Category
import kotlinx.android.synthetic.main.item_layout_category.view.*


class CategoryAdapter(
    var arrayList: ArrayList<Data_Category>,
    var activity: Activity
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
                itemView.linCategoryLayout.background =
                    ContextCompat.getDrawable(activity, R.color.transparent)
            } else {
//                itemView.linCategoryLayout.visibility = View.VISIBLE
                itemView.linCategoryLayout.background =
                    ContextCompat.getDrawable(activity, R.drawable.ppurple_border)

            }

            itemView.imgImage.load(arrayList.full_urlfile) {
                /*    crossfade(750)
                    placeholder(errorPlaceHolder)
                    transformations(CircleCropTransformation())
                    error(errorPlaceHolder)
                    scale(Scale.FILL)*/
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