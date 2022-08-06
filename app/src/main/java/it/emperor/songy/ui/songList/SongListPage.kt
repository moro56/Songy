package it.emperor.songy.ui.songList

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SongListPage() {
    Text(text = "Song list")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SongListPage()
}