package com.example.vitorizkiimanda.footballschedulevri.database

data class FavoriteMatch(val id: Long?, val teamId: String?, val teamName: String?, val teamBadge: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val MATCH_HOME_ID: String = "MATCH_HOME_ID"
        const val MATCH_AWAY_ID: String = "MATCH_AWAY_ID"
        const val MATCH_HOME_NAME: String = "MATCH_HOME_NAME"
        const val MATCH_AWAY_NAME: String = "MATCH_AWAY_NAME"
        const val MATCH_HOME_SCORE: String = "MATCH_HOME_SCORE"
        const val MATCH_AWAY_SCORE: String = "MATCH_AWAY_SCORE"
    }
}