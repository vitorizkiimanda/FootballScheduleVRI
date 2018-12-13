package com.example.vitorizkiimanda.footballschedulevri.matchDetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.kotlinacademy.util.invisible
import com.dicoding.kotlinacademy.util.visible
import com.example.vitorizkiimanda.footballschedulevri.Api.ApiRepository
import com.example.vitorizkiimanda.footballschedulevri.Api.Model.Match
import com.example.vitorizkiimanda.footballschedulevri.Api.Model.Team
import com.example.vitorizkiimanda.footballschedulevri.Api.Model.TeamResponse
import com.example.vitorizkiimanda.footballschedulevri.R
import com.example.vitorizkiimanda.footballschedulevri.R.drawable.ic_add_to_favorites
import com.example.vitorizkiimanda.footballschedulevri.R.drawable.ic_added_to_favorites
import com.example.vitorizkiimanda.footballschedulevri.R.id.add_to_favorite
import com.example.vitorizkiimanda.footballschedulevri.R.menu.detail_menu
import com.example.vitorizkiimanda.footballschedulevri.database.FavoriteMatch
import com.example.vitorizkiimanda.footballschedulevri.database.database
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    private lateinit var presenter: MatchDetailPresenter
    private lateinit var progressBar: ProgressBar
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var matchData: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)


        //binding
        progressBar = findViewById(R.id.progress_bar)

        val data:Match = intent.getParcelableExtra<Match>("match")
        matchData = data

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


        //get picture away
        presenter.getTeamAway(data.strAwayTeam)

        //Toolbar
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    private fun favoriteState(){
        database.use {
            Log.d("checkDB","id :" + matchData.idEvent)
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to matchData.idEvent)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
            setFavorite()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        //check favourite status
        favoriteState()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.EVENT_ID to matchData.idEvent,
                        FavoriteMatch.EVENT_DATE to matchData.dateEvent,
                        FavoriteMatch.HOME_ID to matchData.idHomeTeam,
                        FavoriteMatch.HOME_NAME to matchData.strHomeTeam,
                        FavoriteMatch.HOME_SCORE to matchData.intHomeScore,
                        FavoriteMatch.HOME_GOALS to matchData.strHomeGoalDetails,
                        FavoriteMatch.HOME_SHOTS to matchData.intHomeShots,
                        FavoriteMatch.HOME_KEEPER to matchData.strHomeLineupGoalkeeper,
                        FavoriteMatch.HOME_DEFENSE to matchData.strHomeLineupDefense,
                        FavoriteMatch.HOME_MIDFIELD to matchData.strHomeLineupMidfield,
                        FavoriteMatch.HOME_FORWARD to matchData.strHomeLineupForward,
                        FavoriteMatch.HOME_SUBS to matchData.strHomeLineupSubstitutes,
                        FavoriteMatch.AWAY_ID to matchData.idAwayTeam,
                        FavoriteMatch.AWAY_NAME to matchData.strAwayTeam,
                        FavoriteMatch.AWAY_SCORE to matchData.intAwayScore,
                        FavoriteMatch.AWAY_GOALS to matchData.strAwayGoalDetails,
                        FavoriteMatch.AWAY_SHOTS to matchData.intAwayShots,
                        FavoriteMatch.AWAY_KEEPER to matchData.strAwayLineupGoalkeeper,
                        FavoriteMatch.AWAY_DEFENSE to matchData.strAwayLineupDefense,
                        FavoriteMatch.AWAY_MIDFIELD to matchData.strAwayLineupMidfield,
                        FavoriteMatch.AWAY_FORWARD to matchData.strAwayLineupForward,
                        FavoriteMatch.AWAY_SUBS to matchData.strAwayLineupSubstitutes)
            }
            toast("Added to favourite")
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
//        try {
//            database.use {
//                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(EVENT_ID = {id})",
//                        "id" to matchData.idEvent)
//            }
//            toast("Removed from favourite")
//        } catch (e: SQLiteConstraintException){
//            toast(e.localizedMessage)
//        }
    }


    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

}