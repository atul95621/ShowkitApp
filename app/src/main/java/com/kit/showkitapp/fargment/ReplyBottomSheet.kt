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
import kotlinx.android.synthetic.main.reply_bottom_sheet.view.*


class ReplyBottomSheet(var homeActivity: HomeActivity) : BottomSheetDialogFragment() {

    companion object {

        const val TAG = "ReplyBottomSheetTAG"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.reply_bottom_sheet, container, false)
        view.imgClose.setOnClickListener()
        {
            dismiss()
        }

        return view
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
