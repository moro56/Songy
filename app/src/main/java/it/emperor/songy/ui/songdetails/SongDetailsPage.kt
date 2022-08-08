package it.emperor.songy.ui.songdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun SongDetailsPage(navController: NavHostController, url: String) {
    val state = rememberWebViewState(url)

    Column(modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
        }
        val loadingState = state.loadingState as? LoadingState.Loading
        LinearProgressIndicator(
            progress = loadingState?.progress ?: 0f,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (loadingState != null) 1f else 0f)
        )
        WebView(state, onCreated = { it.settings.javaScriptEnabled = true })
    }
}

@Preview(showSystemUi = true)
@Composable
fun SongDetailsPagePreview() {
    SongDetailsPage(
        rememberNavController(),
        "https://music.apple.com/us/album/free-dem-5s/1617170958?i=1617170971"
    )
}