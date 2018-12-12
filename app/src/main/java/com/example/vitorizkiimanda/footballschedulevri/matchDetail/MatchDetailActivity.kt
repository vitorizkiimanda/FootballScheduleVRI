package com.example.vitorizkiimanda.footballschedulevri.matchDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.kotlinacademy.util.invisible
import com.dicoding.kotlinacademy.util.visible
import com.example.vitorizkiimanda.footballschedulevri.Api.ApiRepository
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.Match
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.Team
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.TeamResponse
import com.example.vitorizkiimanda.footballschedulevri.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    private var team: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MatchDetailPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)


        //binding
        progressBar = findViewById(R.id.progress_bar)

        val data:Match = intent.getParcelableExtra<Match>("match")

        match_date.text = data.dateEvent

        name_home.text = data.strHomeTeam
        score_home.text = data.intHomeScore
        goals_home.text = data.strHomeGoalDetails
        keeper_home.text = data.strHomeLineupGoalkeeper
        defense_home.text = data.strHomeLineupDefense
        midfield_home.text = data.strHomeLineupMidfield
        forward_home.text = data.strHomeLineupForward
        subs_home.text = data.strHomeLineupSubstitutes
        shots_home.text = data.intHomeShots

        name_away.text = data.strAwayTeam
        score_away.text = data.intAwayScore
        goals_away.text = data.strAwayGoalDetails
        keeper_away.text = data.strAwayLineupGoalkeeper
        defense_away.text = data.strAwayLineupDefense
        midfield_away.text = data.strAwayLineupMidfield
        forward_away.text = data.strAwayLineupForward
        subs_away.text = data.strAwayLineupSubstitutes
        shots_away.text = data.intAwayShots

        //get picture home
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, request, gson)
        presenter.getTeamHome(data.strHomeTeam)


        //get picture home
        presenter.getTeamAway(data.strAwayTeam)

    }
    override fun getBadgeHome(data: TeamResponse) {

        Glide.with(applicationContext)
                .load(data.team[0].strTeamBadge)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(image_home)
    }

    override fun getBadgeAway(data: TeamResponse) {
        Glide.with(applicationContext)
                .load(data.team[0].strTeamBadge)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(image_away)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

}