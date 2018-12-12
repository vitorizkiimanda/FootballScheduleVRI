package com.example.vitorizkiimanda.footballschedulevri.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Spinner
import com.dicoding.kotlinacademy.util.invisible
import com.dicoding.kotlinacademy.util.visible
import com.example.vitorizkiimanda.footballschedulevri.Adapter.MatchAdapter
import com.example.vitorizkiimanda.footballschedulevri.Api.ApiRepository
import com.example.vitorizkiimanda.footballschedulevri.Api.Data.Match
import com.example.vitorizkiimanda.footballschedulevri.matchDetail.MatchDetailActivity
import com.example.vitorizkiimanda.footballschedulevri.R
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.startActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 *
 */
class FirstFragment : Fragment(), MainView {

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)


        //binding
        listMatch = view.findViewById(R.id.rvMatches)
        progressBar = view.findViewById(R.id.progress_bar)

        adapter = MatchAdapter(matches)
        listMatch.adapter = adapter

        //layout manager
        listMatch.layoutManager = LinearLayoutManager(context)


        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getLastMatches("4328")

        return view
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
//        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
