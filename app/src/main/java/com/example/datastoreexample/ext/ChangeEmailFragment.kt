package com.example.datastoreexample.ext

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.datastoreexample.R
import com.example.datastoreexample.databinding.FragmentChangeEmailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangeEmailFragment : Fragment() {
    lateinit var binding: FragmentChangeEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_change_email, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val protoDataStore = ProtoDataStore(requireContext())
        binding.apply {
            CoroutineScope(Dispatchers.Main).launch {
                protoDataStore.protoFlow.collect {
                    edtEmail.setText(it)
                }
            }
            btnChnageEmail.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    protoDataStore.clearData()
                }
            }
        }
    }
}