package com.example.aplikasigithubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.aplikasigithubuser.databinding.ActivityMainBinding
import com.example.aplikasigithubuser.view.FavoriteFragment
import com.example.aplikasigithubuser.view.HomeFragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}