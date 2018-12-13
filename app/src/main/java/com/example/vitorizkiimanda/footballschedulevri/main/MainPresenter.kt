package com.example.vitorizkiimanda.footballschedulevri.main

import com.example.vitorizkiimanda.footballschedulevri.Api.ApiRepository
import com.example.vitorizkiimanda.footballschedulevri.Api.Model.MatchResponse
import com.example.vitorizkiimanda.footballschedulevri.Api.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {


//    fun getTeamList(league: String?) {
//        view.showLoading()
//        doAsync {
//            val data = gson.fromJson(apiRepository
//                    .doRequest(TheSportDBApi.getTeams(league)),
//                    TeamResponse::class.java
//            )
//
//            uiThread {
//                view.hideLoading()
//                view.showTeamList(data.teams)
//            }
//        }
//    }

    fun getLastMatches(idLeage: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLastMatches("4328")),
                    MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.matches)
            }
        }
    }

    fun getNextMatches(idLeage: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextMatches("4328")),
                    MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.matches)
            }
        }
    }

}
