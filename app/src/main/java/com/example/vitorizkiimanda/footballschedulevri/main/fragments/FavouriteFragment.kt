package com.example.vitorizkiimanda.footballschedulevri.main.fragments


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.dicoding.kotlinacademy.util.invisible
import com.dicoding.kotlinacademy.util.visible
import com.example.vitorizkiimanda.footballschedulevri.Adapter.FavouriteMatchesAdapter
import com.example.vitorizkiimanda.footballschedulevri.Api.Model.Match

import com.example.vitorizkiimanda.footballschedulevri.R
import com.example.vitorizkiimanda.footballschedulevri.database.FavouriteMatch
import com.example.vitorizkiimanda.footballschedulevri.database.database
import com.example.vitorizkiimanda.footballschedulevri.main.MainPresenter
import com.example.vitorizkiimanda.footballschedulevri.main.MainView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouriteFragment : android.support.v4.app.Fragment(), MainView {
    private var matches: MutableList<FavouriteMatch> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: FavouriteMatchesAdapter
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)


        //binding
        listMatch = view.findViewById(R.id.rvMatches)
        progressBar = view.findViewById(R.id.progress_bar)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)

        adapter = FavouriteMatchesAdapter(matches)
        listMatch.adapter = adapter

        //layout manager
        listMatch.layoutManager = LinearLayoutManager(context)

        //pull to update
        swipeRefreshLayout.onRefresh {
            showFavorite()
        }

        showFavorite()
        return view
    }

    override fun showMatchList(data: List<Match>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    private fun showFavorite(){
        matches.clear()
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            showLoading()
            val result = select(FavouriteMatch.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavouriteMatch>())
            matches.addAll(favorite)
            adapter.notifyDataSetChanged()
            hideLoading()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

}
