package com.yechaoa.gradlex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val packageInfo = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0)
        Log.wtf("yechaoa", "versionName = " + packageInfo.versionName)
        Log.wtf("yechaoa", "versionCode = " + packageInfo.versionCode)
    }

}