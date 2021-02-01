package com.android.basicecommerce.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.android.basicecommerce.R
import com.android.basicecommerce.base.BaseActivity
import com.android.basicecommerce.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        binding.navBottom.setupWithNavController(
            Navigation.findNavController(this, R.id.nav_host_fragment)
        )
        binding.navBottom.setOnNavigationItemSelectedListener { item ->
            onNavDestinationSelected(item, Navigation.findNavController(this, R.id.nav_host_fragment))
        }
    }

}