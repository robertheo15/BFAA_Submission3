package com.example.aplikasigithubuser.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aplikasigithubuser.view.FollowersFragment
import com.example.aplikasigithubuser.view.FollowingFragment

class SectionsPagerAdapter(
    activity: FragmentManager, lifecycle: Lifecycle,
    private val username: Bundle
) : FragmentStateAdapter(activity, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FollowersFragment()
            }
            1 -> {
                fragment = FollowingFragment()
            }
        }
        fragment?.arguments = username
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2
}