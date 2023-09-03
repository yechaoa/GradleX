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

        Log.v("yechaoa","onResume")
        Log.d("yechaoa","onResume")
        Log.i("yechaoa","onResume")
        Log.w("yechaoa","onResume")
        Log.e("yechaoa","onResume")
        Log.wtf("yechaoa wtf","onResume by wtf")
    }

}