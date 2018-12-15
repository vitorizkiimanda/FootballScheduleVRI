package com.example.vitorizkiimanda.footballschedulevri

import com.example.vitorizkiimanda.footballschedulevri.Api.ApiRepository
import com.example.vitorizkiimanda.footballschedulevri.Api.Model.Team
import com.example.vitorizkiimanda.footballschedulevri.Api.Model.TeamResponse
import com.example.vitorizkiimanda.footballschedulevri.Api.TheSportDBApi
import com.example.vitorizkiimanda.footballschedulevri.matchDetail.MatchDetailPresenter
import com.example.vitorizkiimanda.footballschedulevri.matchDetail.MatchDetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {
    @Mock
    private
    lateinit var view: MatchDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson)
    }

    @Test
    fun testGetTeamHome() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val teamName = "Everton"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamName)),
                    TeamResponse::class.java
            )).thenReturn(response)

            presenter.getTeamHome(teamName)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).getBadgeHome(response)
            Mockito.verify(view).hideLoading()
        }
    }
}