package it.emperor.songy.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import it.emperor.songy.navigation.NavDestination
import it.emperor.songy.navigation.value
import it.emperor.songy.ui.songdetails.SongDetailsPage
import it.emperor.songy.ui.songlist.SongListPage
import it.emperor.songy.ui.theme.SongyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            val surfaceColor = MaterialTheme.colors.surface

            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = surfaceColor,
                    darkIcons = useDarkIcons
                )
            }
            SongyTheme {
                Scaffold {
                    NavigationComponent(navController)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.SONG_LIST.value()
    ) {
        composable(NavDestination.SONG_LIST.value()) {
            SongListPage(navController)
        }
        composable(NavDestination.SONG_DETAILS.value()) {
            SongDetailsPage(it.arguments?.getString("url"))
        }
    }
}