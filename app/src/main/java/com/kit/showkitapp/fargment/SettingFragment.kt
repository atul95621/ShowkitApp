package com.kit.showkitapp.fargment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kit.showkitapp.HomeActivity
import com.kit.showkitapp.R
import kotlinx.android.synthetic.main.fragment_setting.view.*

class SettingFragment(var homeActivity: HomeActivity) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeActivity.setTitleName("Setting")
        homeActivity.showHeader()

        view.linFollow.setOnClickListener()
        {
            homeActivity.openFragment(FollowInviteFriendFragment(homeActivity))
        }
        view.linHelp.setOnClickListener()
        {
            homeActivity.openFragment(HelpFragment(homeActivity))
        }
        view.linAbout.setOnClickListener()
        {
            homeActivity.openFragment(AboutFragment(homeActivity))
        }
        view.linSecurity.setOnClickListener()
        {
            homeActivity.openFragment(SecurityFragment(homeActivity))
        }
        view.linNotification.setOnClickListener()
        {
            homeActivity.openFragment(NotificationsFragment(homeActivity))
        }
        view.linPrivacy.setOnClickListener()
        {
            homeActivity.openFragment(PrivacyFragment(homeActivity))
        }
        view.linTheme.setOnClickListener()
        {
            homeActivity.openFragment(ThemeFragment(homeActivity))
        }
        view.linAccount.setOnClickListener()
        {
            homeActivity.openFragment(AccountFragment(homeActivity))
        }
    }
}