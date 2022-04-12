package com.example.aplikasigithubuser.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.local.entity.Favorite
import com.example.aplikasigithubuser.databinding.FragmentUserDetailBinding
import com.example.aplikasigithubuser.model.User
import com.example.aplikasigithubuser.utils.Helper
import com.example.aplikasigithubuser.view.adapter.SectionsPagerAdapter
import com.example.aplikasigithubuser.viewmodel.DetailUserViewModel
import com.example.aplikasigithubuser.viewmodel.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding

    private lateinit var username: String
    private lateinit var detailUserViewModel: DetailUserViewModel

    private val helper = Helper()
    private var buttonState: Boolean = false
    private var favorite: Favorite? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setUsername()
        setViewPager()
        detailUserViewModel = obtainViewModel(this)
        detailUserViewModel.getDetailUser(username)
        detailUserViewModel.findById(username).observe(viewLifecycleOwner) { favoriteData ->
            buttonState = favoriteData.isNotEmpty()
            if (buttonState) {
                binding?.buttonFavorite?.setImageResource(R.drawable.ic_favorite_active)
            } else {
                binding?.buttonFavorite?.setImageResource(R.drawable.ic_favorite_border)
            }
        }
        detailUserViewModel.user.observe(viewLifecycleOwner) { user ->
            setUser(user)
            binding?.buttonFavorite?.setOnClickListener {
                if (!buttonState) {
                    buttonState = true
                    binding?.buttonFavorite?.setImageResource(R.drawable.ic_favorite_active)

                    favorite = Favorite(
                        user.username,
                        user.name,
                        user.avatarUrl,
                        user.followers,
                        user.following,
                        user.followersUrl,
                        user.followingUrl,
                        user.repository,
                        user.location,
                        user.company,
                        user.id
                    )
                    detailUserViewModel.create(favorite as Favorite)
                    Toast.makeText(
                        context,
                        "User Added to Favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    buttonState = false
                    binding?.buttonFavorite?.setImageResource(R.drawable.ic_favorite_border)
                    detailUserViewModel.delete(user.id)
                    Toast.makeText(
                        context,
                        "User Deleted From Favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        detailUserViewModel.isLoading.observe(viewLifecycleOwner) {
            binding?.progressBarGroup?.let { it1 ->
                helper.isLoading(
                    it,
                    it1
                )
            }
        }
        binding?.buttonFavorite?.setOnClickListener {
            binding?.buttonFavorite?.setImageResource(R.drawable.ic_favorite_active)
        }

    }

    private fun setUsername() {
        val user = arguments?.getParcelable<User>(HomeFragment.EXTRA_USER) as User
        this.username = user.username.toString()
    }

    private fun setUser(user: User) {
        binding?.userName?.text = StringBuilder("@").append(user.username)
        binding?.name?.text = user.name
        binding?.company?.text = user.company ?: "-----"
        binding?.location?.text = user.location ?: "-----"
        binding?.repository?.text = StringBuilder(user.repository.toString()).append(" Repository")
        binding?.followers?.text = StringBuilder(user.followers.toString()).append(" Followers")
        binding?.following?.text = StringBuilder(user.following.toString()).append(" Following")
        binding?.imageProfile?.let {
            Glide.with(this.requireContext())
                .load(user.avatarUrl)
                .placeholder(R.drawable.ic_block)
                .into(it)
        }
    }

    private fun setViewPager() {

        val viewPager: ViewPager2? = view?.findViewById(R.id.view_pager)
        val tabs: TabLayout? = view?.findViewById(R.id.tabs)

        val username = Bundle()
        username.putString(EXTRA_USERNAME, this.username)

        val sectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager, lifecycle, username)

        viewPager?.adapter = sectionsPagerAdapter
        if (viewPager != null && tabs != null) {
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    private fun obtainViewModel(activity: Fragment): DetailUserViewModel {
        val factory = ViewModelFactory.getInstance(activity.requireActivity().application)
        return ViewModelProvider(activity, factory)[DetailUserViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }
}