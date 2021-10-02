package com.example.archpattern.mvc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.archpattern.BaseActivity
import com.example.archpattern.R

class MVCActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvcactivity)
    }

    override fun getArchIntent(context: Context): Intent {
        return Intent(context, this::class.java)
    }
}