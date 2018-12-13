package com.example.vitorizkiimanda.footballschedulevri.matchDetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
import com.example.vitorizkiimanda.footballschedulevri.R.drawable.ic_add_to_favorites
import com.example.vitorizkiimanda.footballschedulevri.R.drawable.ic_added_to_favorites
import com.example.vitorizkiimanda.footballschedulevri.R.id.add_to_favorite
import com.example.vitorizkiimanda.footballschedulevri.R.menu.detail_menu
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.toolbar

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    private var team: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MatchDetailPresenter
    private lateinit var progressBar: ProgressBar
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
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
//        try {
//            database.use {
//                insert(Favorite.TABLE_FAVORITE,
//                        Favorite.TEAM_ID to teams.teamId,
//                        Favorite.TEAM_NAME to teams.teamName,
//                        Favorite.TEAM_BADGE to teams.teamBadge)
//            }
//            swipeRefresh.snackbar("Added to favorite").show()
//        } catch (e: SQLiteConstraintException){
//            swipeRefresh.snackbar(e.localizedMessage).show()
//        }


        toast("Added to favourite")
    }

    private fun removeFromFavorite(){
//        try {
//            database.use {
//                delete(Favorite.TABLE_FAVORITE, "(TEAM_ID = {id})",
//                        "id" to id)
//            }
//            swipeRefresh.snackbar( "Removed to favorite").show()
//        } catch (e: SQLiteConstraintException){
//            swipeRefresh.snackbar(e.localizedMessage).show()
//        }


        toast("Removed from favourite")
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

}