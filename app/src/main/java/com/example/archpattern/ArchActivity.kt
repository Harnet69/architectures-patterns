package com.example.archpattern

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.archpattern.MVIActivity.MVIActivity
import com.example.archpattern.databinding.ArchActivityBinding
import com.example.archpattern.mvc.MVCActivity
import com.example.archpattern.mvp.MVPActivity
import com.example.archpattern.mvvm.MVVMActivity

class ArchActivity : AppCompatActivity() {
    private lateinit var binding: ArchActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickAndGoToActivity(this)
    }

    private fun clickAndGoToActivity(context: Context) {
        binding.mvc.setOnClickListener { startActivity(MVCActivity().getArchIntent(context)) }
        binding.mvp.setOnClickListener { startActivity(MVPActivity().getArchIntent(context)) }
        binding.mvvm.setOnClickListener { startActivity(MVVMActivity().getArchIntent(context)) }
        binding.mvi.setOnClickListener { startActivity(MVIActivity().getArchIntent(context)) }
    }
}