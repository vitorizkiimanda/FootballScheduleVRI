package com.example.vitorizkiimanda.footballschedulevri


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Spinner
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import org.jetbrains.anko.support.v4.startActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var listTeam: RecyclerView
private lateinit var progressBar: ProgressBar
private lateinit var swipeRefresh: SwipeRefreshLayout
private lateinit var spinner: Spinner

/**
 * A simple [Fragment] subclass.
 *
 */
class FirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        view.item_list.setOnClickListener(clickListener)

        return view
    }


    val clickListener = View.OnClickListener {view ->

        when (view.getId()) {
            R.id.item_list -> startActivity<MatchDetailActivity>()
        }
    }


}
