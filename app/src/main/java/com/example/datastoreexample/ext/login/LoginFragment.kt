package com.example.datastoreexample.ext.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.datastoreexample.R
import com.example.datastoreexample.databinding.FragmentLoginBinding
import com.example.datastoreexample.ext.PreferenceKey.IS_LOGGEDIN
import com.example.datastoreexample.ext.PreferenceKey.NAME
import com.example.datastoreexample.ext.PreferenceKey.PASSWORD
import com.example.datastoreexample.ext.ProtoDataStore
import com.example.datastoreexample.ext.home.HomeFragment
import com.example.datastoreexample.ext.main.MainActivity
import com.example.datastoreexample.ext.splash.UserPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var pref : UserPreferences
    private lateinit var proto : ProtoDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = UserPreferences(requireContext())
        proto = ProtoDataStore(requireContext())
        binding.apply {
            btnLogin.setOnClickListener(this@LoginFragment)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnLogin -> {
                if (Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text).matches()) {
                    if (binding.edtPwd.text.length > 4) {
                        CoroutineScope(Dispatchers.IO).launch {
                            pref.setBoolean(true,IS_LOGGEDIN)
                            pref.setString(binding.edtName.text.toString(), NAME)
                            pref.setString(binding.edtPwd.text.toString(), PASSWORD)
                            proto.setEmail(binding.edtEmail.text.toString())
                            (activity as MainActivity).changeFragment(HomeFragment())
                        }
                    }
                }
            }
        }
    }
}
