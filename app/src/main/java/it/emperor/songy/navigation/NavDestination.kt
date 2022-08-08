package it.emperor.songy.navigation

enum class NavDestination {
    SONG_LIST,
    SONG_DETAILS
}

fun NavDestination.value() = when (this) {
    NavDestination.SONG_LIST -> "songList"
    NavDestination.SONG_DETAILS -> "songDetails/{url}"
}

fun NavDestination.destination(vararg params: String) = when (this) {
    NavDestination.SONG_LIST -> this.value()
    NavDestination.SONG_DETAILS -> "songDetails/${params[0]}"
}