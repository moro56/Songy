package it.emperor.songy.ui.songlist.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.emperor.songy.R

@Composable
fun SongListErrorLayout(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message, style = MaterialTheme.typography.body2, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            textAlign = TextAlign.Center
        )
        TextButton(onClick = { onRetry.invoke() }) {
            Text(
                text = stringResource(id = R.string.song_list_retry_btn),
                style = MaterialTheme.typography.button
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SongListErrorLayoutPreview() {
    SongListErrorLayout("Error") {}
}