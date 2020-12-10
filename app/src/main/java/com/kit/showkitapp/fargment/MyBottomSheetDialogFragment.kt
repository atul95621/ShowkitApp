package com.kit.showkitapp.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kit.showkitapp.HomeActivity
import com.kit.showkitapp.R
import kotlinx.android.synthetic.main.bottom_sheet_persistent.view.*
import kotlinx.android.synthetic.main.item_layout_trending.*
import kotlinx.android.synthetic.main.item_layout_trending.view.*


class MyBottomSheetDialogFragment(var homeActivity: HomeActivity) : BottomSheetDialogFragment() {

    companion object {

        const val TAG = "CustomBottomSheetDialogFragment"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.bottom_sheet_persistent, container, false)
        view.imgClose.setOnClickListener()
        {
            dismiss()
        }
        view.tvReply.setOnClickListener()
        {
            dismiss()
            homeActivity.openReplyBottomSheet()
        }

        /*  view.imgMenuOtion.setOnClickListener()
              {
                  showPopUpMenu(view)
              }*/
        return view
    }

    fun showPopUpMenu(view: Any) {

        val popup = PopupMenu(context, homeActivity.findViewById(R.id.imgMenuOtion))
        //Inflating the Popup using xml file
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_bottom_sheet, popup.getMenu())

        //registering popup with OnMenuItemClickListener

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                Toast.makeText(
                    context,
                    "You Clicked : " + item.getTitle(),
                    Toast.LENGTH_SHORT
                ).show()
                return true
            }
        })

        popup.show() //showing popup menu

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val options = listOf<String>(
            "Share with Friends",
            "Bookmark",
            "Add to Favourites",
            "More Information"
        )

        /*      listViewOptions.adapter = ArrayAdapter<String>(
                  requireContext(),
                  android.R.layout.simple_list_item_1,
                  options
              )*/


    }
}
