package com.yechaoa.gradlex

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.yechaoa.yutilskt.LogUtil
import com.yechaoa.yutilskt.ToastUtil
import com.yechaoa.yutilskt.YUtils
import com.yechaoa.yutilskt.show

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        YUtils.init(application)
        LogUtil.setIsLog(true);

        findViewById<TextView>(R.id.tv_text).setOnClickListener {
            LogUtil.e("YUtils.init")

            showDialog()

            requestPermission()
        }
    }

    private fun requestPermission() {
        requestPermissions(arrayOf(android.Manifest.permission.RECORD_AUDIO), 1000)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                ToastUtil.show("权限申请成功")
            } else {
                ToastUtil.show("权限申请失败")
            }
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this@MainActivity).apply {
            setTitle("有新版本")
            setMessage("请问要现在升级吗？")
            setPositiveButton("确定") { _, _ ->

            }
            setNegativeButton("取消", null)
            create()
            show()
        }
    }

    override fun onResume() {
        super.onResume()

        val packageInfo = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0)
        Log.wtf("yechaoa", "versionName = " + packageInfo.versionName)
        Log.wtf("yechaoa", "versionCode = " + packageInfo.versionCode)
        Log.wtf("yechaoa", "packageName = " + packageInfo.packageName)
        Log.wtf("yechaoa", "applicationInfo = " + packageInfo.applicationInfo)
    }

}