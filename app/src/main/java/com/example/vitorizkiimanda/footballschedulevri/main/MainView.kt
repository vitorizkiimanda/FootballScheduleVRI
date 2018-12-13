package com.example.vitorizkiimanda.footballschedulevri.main

import com.example.vitorizkiimanda.footballschedulevri.Api.Model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
//    fun showTeamList(data: List<Team>)
    fun showMatchList(data: List<Match>)
}