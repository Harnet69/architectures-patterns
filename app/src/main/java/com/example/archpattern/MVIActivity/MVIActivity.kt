package com.example.archpattern.MVIActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.archpattern.BaseActivity
import com.example.archpattern.R

class MVIActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvcactivity)
    }

    override fun getArchIntent(context: Context): Intent {
        return Intent(context, this::class.java)
    }
}