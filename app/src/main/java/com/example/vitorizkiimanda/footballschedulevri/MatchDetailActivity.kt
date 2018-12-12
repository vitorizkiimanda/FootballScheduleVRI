package com.example.vitorizkiimanda.footballschedulevri

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.Match

class MatchDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)


        val data:Match = intent.getParcelableExtra<Match>("match")
        Log.d("detail page", "data detail :"+ data)
    }
}
