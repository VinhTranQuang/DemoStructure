package com.android.example.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.example.R
import com.android.example.util.replaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            replaceMainPage()
        }
    }

    private fun replaceMainPage() {
        replaceFragment(R.id.frame_container, HomeFragment.newInstance(), HomeFragment.FRAGMENT_NAME, false)
    }

}
