package id.jason.kotlinexpert.helper

import id.jason.kotlinexpert.BuildConfig

class Constants {
    object IntentBundle{
        const val LEAGUE_ID = "league_id"
        const val EVENT_ID = "event_id"
        const val EVENT_SEARCH = "event_search"
    }

    object ApiEndPoint {
        const val BASE_URL = BuildConfig.BASE_URL
        const val PREVIOUS_MATCH = "eventspastleague.php"
        const val NEXT_MATCH = "eventsnextleague.php"
        const val MATCH_DETAIL = "lookupevent.php"
        const val SEARCH_EVENT = "searchevents.php"
    }
}