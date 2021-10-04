package com.example.archpattern.mvi

import android.os.Bundle
import com.example.archpattern.BaseActivity
import com.example.archpattern.databinding.ActivityMviactivityBinding

class MVIActivity : BaseActivity() {
    private lateinit var binding: ActivityMviactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMviactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}