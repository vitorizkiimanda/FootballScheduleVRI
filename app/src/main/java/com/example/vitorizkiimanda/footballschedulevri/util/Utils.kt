package com.dicoding.kotlinacademy.util

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}


@SuppressLint("SimpleDateFormat")
fun formatDate(date: String?): String{
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return SimpleDateFormat("EEE, dd MMM yyyy").format(sdf.parse(date) as Date)
}