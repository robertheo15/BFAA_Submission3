package com.example.aplikasigithubuser.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasigithubuser.databinding.FragmentFollowersBinding
import com.example.aplikasigithubuser.model.User
import com.example.aplikasigithubuser.utils.Helper
import com.example.aplikasigithubuser.view.adapter.FollowersAdapter
import com.example.aplikasigithubuser.viewmodel.FollowersViewModel

class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding
    private val followersViewModel by viewModels<FollowersViewModel>()
    private val helper = Helper()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowersBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followersViewModel.isLoading.observe(viewLifecycleOwner) {
            binding?.progressBarFollowers?.let { it1 -> helper.isLoading(it, it1) }
        }

        followersViewModel.listUser.observe(viewLifecycleOwner) { followerList ->
            setRecyclerData(followerList)
        }

        followersViewModel.getFollowers(
            arguments?.getString(UserDetailFragment.EXTRA_USERNAME).toString()
        )
    }

    private fun setRecyclerData(list: List<User>) {

        val followersList = ArrayList<User>()
        for (user in list) {
            followersList.clear()
            followersList.addAll(list)
        }

        binding?.rvFollowers?.layoutManager = LinearLayoutManager(context)
        val followersAdapter = FollowersAdapter(followersList)
        binding?.rvFollowers?.adapter = followersAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}