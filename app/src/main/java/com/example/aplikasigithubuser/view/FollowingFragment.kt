package com.example.aplikasigithubuser.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasigithubuser.databinding.FragmentFollowingBinding
import com.example.aplikasigithubuser.model.User
import com.example.aplikasigithubuser.utils.Helper
import com.example.aplikasigithubuser.view.adapter.FollowingAdapter
import com.example.aplikasigithubuser.viewmodel.FollowingViewModel

class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding
    private val followingViewModel by viewModels<FollowingViewModel>()
    private val helper = Helper()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followingViewModel.isLoading.observe(viewLifecycleOwner) {
            helper.isLoading(it, binding.progressBarFollowing)
        }
        followingViewModel.listUser.observe(viewLifecycleOwner) { followingList ->
            setRecyclerData(followingList)
        }
        followingViewModel.getFollowing(
            arguments?.getString(UserDetailFragment.EXTRA_USERNAME).toString()
        )
    }

    private fun setRecyclerData(list: List<User>) {

        with(binding) {
            val followingList = ArrayList<User>()
            for (user in list) {
                followingList.clear()
                followingList.addAll(list)
            }

            rvFollowing.layoutManager = LinearLayoutManager(context)
            val followingAdapter = FollowingAdapter(followingList)
            rvFollowing.adapter = followingAdapter
        }
    }
}