package com.example.aplikasigithubuser.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

import androidx.lifecycle.ViewModelProvider
import com.example.aplikasigithubuser.databinding.FragmentSettingBinding
import com.example.aplikasigithubuser.utils.SettingPreferences
import com.example.aplikasigithubuser.viewmodel.SettingViewModel
import com.example.aplikasigithubuser.viewmodel.SettingViewModelFactory

class SettingFragment : Fragment() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Setting "

        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        val settingViewModel = ViewModelProvider(this, SettingViewModelFactory(pref))[SettingViewModel::class.java]
        settingViewModel.getThemeSettings().observe(viewLifecycleOwner
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding?.switchTheme?.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding?.switchTheme?.isChecked = false
            }
        }

        binding?.switchTheme?.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
        }
    }
}