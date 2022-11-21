package com.yechaoa.gradlex

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/11/15.
 * Describe : kotlin dsl sample
 */

private fun setPrintln(doPrintln: (String) -> Unit) {
    doPrintln.invoke("yechaoa")
}

fun main() {
    setPrintln { param ->
        println(param)
    }
}