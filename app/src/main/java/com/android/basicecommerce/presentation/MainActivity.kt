package com.android.basicecommerce.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.basicecommerce.R
import com.android.basicecommerce.base.BaseActivity
import com.android.basicecommerce.databinding.ActivityMainBinding
import com.android.basicecommerce.presentation.home.HomeFragment
import com.android.basicecommerce.presentation.profile.ProfileFragment

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

        val homeFragment = HomeFragment.newInstance()
        val profileFragment = ProfileFragment.newInstance()

        setCurrentFragment(homeFragment)

        binding.navBottom.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> setCurrentFragment(homeFragment)
                R.id.menu_profile -> setCurrentFragment(profileFragment)
                else -> showAlertMessage(
                    getString(R.string.label_alert_title),
                    getString(R.string.label_alert_menu_unavailable),
                    getString(R.string.label_alert_neutral_button))
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager
        .beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
}