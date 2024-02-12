package com.example.datastoreexample.ext.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import com.example.datastoreexample.R
import com.example.datastoreexample.ext.PreferenceKey.IS_LOGGEDIN
import com.example.datastoreexample.ext.home.HomeFragment
import com.example.datastoreexample.ext.login.LoginFragment
import com.example.datastoreexample.ext.splash.SplashFragment
import com.example.datastoreexample.ext.splash.UserPreferences

class MainActivity : AppCompatActivity() {
    lateinit var preferences: UserPreferences
    var loggedIn: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(SplashFragment())
        preferences = UserPreferences(this)
        preferences.getBooleanData(IS_LOGGEDIN).asLiveData().observe(this) {
            loggedIn = it
            Log.d("tag", it.toString())
        }
        Handler(Looper.getMainLooper()).postDelayed({
            if (loggedIn) {
                changeFragment(HomeFragment())
            } else {
                changeFragment(LoginFragment())
            }
        }, 4000)
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }
}
