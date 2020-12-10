package com.kit.showkitapp.adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kit.showkitapp.HomeActivity
import com.kit.showkitapp.R
import com.kit.showkitapp.fargment.TrendingFragment
import com.kit.showkitapp.model.TrendingModel
import kotlinx.android.synthetic.main.item_layout_trending.view.*


class TrendingAdapter(
    var arrayList: ArrayList<TrendingModel>,
    var homeActivity: HomeActivity,
   var trendingFragment: TrendingFragment
) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_trending, parent, false)

        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: TrendingAdapter.ViewHolder, position: Int) {
        holder.bind(position, arrayList[position])
    }


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arrayList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, arrayList: TrendingModel) {
            itemView.tvUsername.setText(arrayList.name)

            itemView.imgOption.setOnClickListener()
            {
                val popup = PopupMenu(homeActivity, itemView.imgOption)
                //Inflating the Popup using xml file
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu())

                //registering popup with OnMenuItemClickListener

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem): Boolean {
                        Toast.makeText(
                            homeActivity,
                            "You Clicked : " + item.getTitle(),
                            Toast.LENGTH_SHORT
                        ).show()
                        return true
                    }
                })

                popup.show() //showing popup menu
            }

            itemView.tvAddComment.setOnClickListener()
            {
                homeActivity.openBottomSheet()
//                trendingFragment.opencloseBottomSheetDailog()
            }

        }

    }
}