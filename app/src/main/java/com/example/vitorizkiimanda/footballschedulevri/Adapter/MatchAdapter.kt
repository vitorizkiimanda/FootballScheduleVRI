package com.example.vitorizkiimanda.footballschedulevri.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vitorizkiimanda.footballschedulevri.Api.Model.Match
import com.example.vitorizkiimanda.footballschedulevri.matchDetail.MatchDetailActivity
import com.example.vitorizkiimanda.footballschedulevri.R
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.startActivity

class MatchAdapter (private val matches: List<Match>)
    : RecyclerView.Adapter<MatchViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MatchViewHolder {
        val viewHolder = MatchViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.item_match, p0, false))

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            p0?.context?.startActivity<MatchDetailActivity>("match" to matches[position])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return matches.size
    }


    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matches[position])
    }

}

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val nameHome = view?.name_home
    private val nameAway = view?.name_away
    private val scoreHome = view?.score_home
    private val scoreAway = view?.score_away
    private val match_date = view?.match_date

    fun bindItem(matches: Match) {
//        this.matches = matches
        nameHome?.text = matches.strHomeTeam
        nameAway?.text = matches.strAwayTeam
        scoreHome?.text = matches.intHomeScore
        scoreAway?.text = matches.intAwayScore
        match_date?.text = matches.dateEvent
    }
}