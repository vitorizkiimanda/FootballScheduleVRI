package com.example.vitorizkiimanda.footballschedulevri

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.Match
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.find

class MatchDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)


        val data:Match = intent.getParcelableExtra<Match>("match")
        Log.d("detail page", "data detail :"+ data)

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

    }

}
