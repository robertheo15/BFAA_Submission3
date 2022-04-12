package com.example.aplikasigithubuser.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.local.entity.Favorite
import com.example.aplikasigithubuser.databinding.FragmentFavoriteBinding
import com.example.aplikasigithubuser.model.User
import com.example.aplikasigithubuser.view.adapter.FavoriteAdapter
import com.example.aplikasigithubuser.viewmodel.FavoriteViewModel
import com.example.aplikasigithubuser.viewmodel.ViewModelFactory
import java.util.ArrayList

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Favorite"

        favoriteViewModel = obtainViewModel(this)
        favoriteViewModel.findAll().observe(viewLifecycleOwner) { favoriteList ->
            setRecyclerData(favoriteList)
        }
    }

    private fun setRecyclerData(list: List<Favorite>) {

        val favoriteList = ArrayList<Favorite>()

        for (user in list) {
            favoriteList.clear()
            favoriteList.addAll(list)
        }

        binding?.rvFavorite?.layoutManager = LinearLayoutManager(context)
        val favoriteAdapter = FavoriteAdapter(favoriteList)
        binding?.rvFavorite?.adapter = favoriteAdapter

        favoriteAdapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                showUser(data)
            }
        })
    }


    private fun showUser(data: User) {
        val moveDataUser = Bundle()
        moveDataUser.putParcelable(HomeFragment.EXTRA_USER, data)
        NavHostFragment
            .findNavController(this)
            .navigate(R.id.action_favoriteFragment_to_userDetailFragment, moveDataUser)
    }

    private fun obtainViewModel(activity: Fragment): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.requireActivity().application)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}