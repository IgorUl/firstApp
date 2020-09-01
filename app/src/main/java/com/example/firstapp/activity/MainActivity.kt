package com.example.firstapp.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.fragments.MainFragment
import com.example.firstapp.fragments.SortFragment

class MainActivity : AppCompatActivity(), MainContract.OnScreenChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_main)

        if (savedInstanceState == null) {
            if (isTablet()) initTabletScreen() else initPhoneScreen()
        }
    }

    private fun initPhoneScreen() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MainFragment())
            .commit()
    }

    private fun initTabletScreen() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MainFragment())
            .add(R.id.sort_container, SortFragment())
            .commit()
    }

    override fun onScreenChange() {
        if (isTablet()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.sort_container, SortFragment())
                .commit()
        }
    }

    private fun isTablet(): Boolean =
        (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE
}
