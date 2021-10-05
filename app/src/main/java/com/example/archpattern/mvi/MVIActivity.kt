package com.example.archpattern.mvi

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.archpattern.BaseActivity
import com.example.archpattern.R
import com.example.archpattern.databinding.ActivityMviactivityBinding

class MVIActivity : BaseActivity() {
    private lateinit var binding: ActivityMviactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMviactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onError(errorMsg: String?){
        binding.list.isVisible = false
        binding.progressBar.isVisible = false
        Toast.makeText(this,resources.getString(R.string.error_network), Toast.LENGTH_LONG).show()
        Log.i("UniReceived", "onSuccess: $errorMsg")

    }
}