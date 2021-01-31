package com.android.basicecommerce.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.android.basicecommerce.R
import com.android.basicecommerce.databinding.ActivityMainBinding
import com.android.basicecommerce.presentation.home.HomeFragment
import com.android.basicecommerce.presentation.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val homeFragment = HomeFragment.newInstance()
        val profileFragment = ProfileFragment.newInstance("tes3", "tes4")

        setCurrentFragment(homeFragment)

        binding.navBottom.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> setCurrentFragment(homeFragment)
                R.id.menu_profile -> setCurrentFragment(profileFragment)
                else -> Toast.makeText(this, "Menu belum tersedia", Toast.LENGTH_SHORT).show()
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