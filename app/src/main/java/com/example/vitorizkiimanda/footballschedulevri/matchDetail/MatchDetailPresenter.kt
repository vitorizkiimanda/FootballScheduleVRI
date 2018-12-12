package com.example.vitorizkiimanda.footballschedulevri.matchDetail

import android.util.Log
import com.example.vitorizkiimanda.footballschedulevri.Api.ApiRepository
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.Team
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.TeamResponse
import com.example.vitorizkiimanda.footballschedulevri.Api.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun getTeamHome(teamName: String?) {

        Log.d("detail", "name home team : " + teamName)
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamHome(teamName)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()

                Log.d("detail", "data home team : " + data)
                view.getBadgeHome(data)
            }
        }
    }

}