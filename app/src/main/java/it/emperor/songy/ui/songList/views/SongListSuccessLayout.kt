package it.emperor.songy.ui.songList.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import it.emperor.songy.data.models.Song

@Composable
fun SongListSuccessLayout(songList: List<Song>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 32.dp)
    ) {
        items(songList) {
            SongView(it)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SongListSuccessLayoutPreview() {
    SongListSuccessLayout(
        listOf(
            Song(
                "artistName",
                "name",
                "release",
                "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/4d/54/ec/4d54eccd-8e71-4217-cb81-445adc77136b/075679750006.jpg/100x100bb.jpg"
            )
        )
    )
}


@Composable
fun SongView(song: Song) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        GlideImage(
            imageModel = song.artworkUrl100,
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp)),
            previewPlaceholder = 0
        )
        Text(
            text = song.artistName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = song.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            fontSize = 12.sp
        )
        Text(
            text = song.releaseDate,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            fontSize = 11.sp,
            color = Color.Gray
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SongViewPreview() {
    SongView(
        Song(
            "artistName",
            "name",
            "release",
            "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/4d/54/ec/4d54eccd-8e71-4217-cb81-445adc77136b/075679750006.jpg/100x100bb.jpg"
        )
    )
}