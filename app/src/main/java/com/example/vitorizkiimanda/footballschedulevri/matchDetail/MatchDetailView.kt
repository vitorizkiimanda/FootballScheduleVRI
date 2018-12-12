package com.example.vitorizkiimanda.footballschedulevri.matchDetail

import com.example.vitorizkiimanda.footballschedulevri.Api.Data.Match
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.Team
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.TeamResponse

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
//    fun showTeamList(data: List<Team>)
    fun getBadgeHome(data: TeamResponse)
    fun getBadgeAway(data: TeamResponse)
}