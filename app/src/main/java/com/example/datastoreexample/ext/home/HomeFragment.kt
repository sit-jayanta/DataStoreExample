package com.example.datastoreexample.ext.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import com.example.datastoreexample.R
import com.example.datastoreexample.databinding.FragmentHomeBinding
import com.example.datastoreexample.ext.ChangeEmailFragment
import com.example.datastoreexample.ext.PreferenceKey.NAME
import com.example.datastoreexample.ext.PreferenceKey.PASSWORD
import com.example.datastoreexample.ext.ProtoDataStore
import com.example.datastoreexample.ext.login.LoginFragment
import com.example.datastoreexample.ext.main.MainActivity
import com.example.datastoreexample.ext.splash.UserPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentHomeBinding
    lateinit var preferences: UserPreferences
    lateinit var proto: ProtoDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = UserPreferences(requireContext())
        proto = ProtoDataStore(requireContext())

        //getting name as live data in setting its value to the text view.
        preferences.getStringData(NAME).asLiveData().observe(requireActivity()) {
            binding.name = it
        }

        //getting the value of password here as live data and setting it to the textview.
        preferences.getStringData(PASSWORD).asLiveData().observe(requireActivity()) {
            binding.pwd = it
        }
        binding.clearData.setOnClickListener(this)
        binding.changeEmail.setOnClickListener(this)
        binding.btnlogOut.setOnClickListener(this)

        // data changed in change email fragment also change here
        CoroutineScope(Dispatchers.Main).launch {
            proto.protoFlow.collect{
                Log.d("email",it)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.clearData -> {
                clearPref()
            }
            binding.changeEmail -> {
                (activity as MainActivity).changeFragment(ChangeEmailFragment())
            }
            binding.btnlogOut -> {
                clearPref()
                clearProto()
                (activity as MainActivity).changeFragment(LoginFragment())

            }
        }
    }

    private fun clearPref(){
        CoroutineScope(Dispatchers.Main).launch {
            preferences.clearPreference()
        }
    }
    private fun clearProto(){
        CoroutineScope(Dispatchers.Main).launch {
            proto.clearData()
        }
    }
}
