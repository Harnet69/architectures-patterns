package com.example.archpattern.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.archpattern.BaseActivity
import com.example.archpattern.R

class MVPActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvpactivity)
    }

    override fun getArchIntent(context: Context): Intent {
        return Intent(context, this::class.java)
    }
}