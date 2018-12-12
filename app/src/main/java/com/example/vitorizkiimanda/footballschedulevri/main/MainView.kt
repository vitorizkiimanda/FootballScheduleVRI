package com.example.vitorizkiimanda.footballschedulevri.main

import com.example.vitorizkiimanda.footballschedulevri.Api.Data.Match
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
//    fun showTeamList(data: List<Team>)
    fun showMatchList(data: List<Match>)
}