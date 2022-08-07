package it.emperor.songy.ui.songlist.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
fun SongListSuccessLayout(
    songList: List<Song>,
    canShowMore: Boolean,
    onClick: (String) -> Unit,
    onShowMore: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 32.dp)
        ) {
            items(songList) {
                SongView(it, onClick)
            }
            if (canShowMore) {
                renderLoading { onShowMore.invoke() }
            }
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
                "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/4d/54/ec/4d54eccd-8e71-4217-cb81-445adc77136b/075679750006.jpg/100x100bb.jpg",
                "https://music.apple.com/us/album/free-dem-5s/1617170958?i=1617170971"
            )
        ), true, {}, {}
    )
}


@Composable
fun SongView(song: Song, onClick: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable { onClick.invoke(song.url) }
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
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
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
            "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/4d/54/ec/4d54eccd-8e71-4217-cb81-445adc77136b/075679750006.jpg/100x100bb.jpg",
            "https://music.apple.com/us/album/free-dem-5s/1617170958?i=1617170971"
        )
    ) {}
}

private fun LazyGridScope.renderLoading(onShown: () -> Unit) {
    item(span = { GridItemSpan(3) }) {
        Box(
            modifier = Modifier.height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            SideEffect {
                onShown.invoke()
            }
            CircularProgressIndicator()
        }
    }
}