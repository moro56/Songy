package it.emperor.songy.ui.songdetails

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SongDetailsPage(url: String?) {
    Text(text = url ?: "")
}

@Preview(showSystemUi = true)
@Composable
fun SongDetailsPagePreview() {
    SongDetailsPage("https://music.apple.com/us/album/free-dem-5s/1617170958?i=1617170971")
}