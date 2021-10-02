package com.example.archpattern

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    abstract fun getArchIntent(context: Context): Intent
}