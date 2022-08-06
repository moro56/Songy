package it.emperor.songy.ui.songList

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel

@Composable
fun SongListPage() {
    val viewModel = getViewModel<SongListPageViewModel>()
    Text(text = "Song list")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SongListPage()
}