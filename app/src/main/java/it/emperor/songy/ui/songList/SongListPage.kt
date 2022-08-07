package it.emperor.songy.ui.songList

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.emperor.songy.R
import it.emperor.songy.data.network.models.ApiResponse
import it.emperor.songy.ui.songList.state.SongListPageState
import it.emperor.songy.ui.songList.views.SongListErrorLayout
import it.emperor.songy.ui.songList.views.SongListLoadingLayout
import it.emperor.songy.ui.songList.views.SongListSuccessLayout
import org.koin.androidx.compose.getViewModel

@Composable
fun SongListPage() {
    val viewModel = getViewModel<SongListPageViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.loadSongs()
    }

    SongListLayout(uiState) {
        viewModel.loadSongs()
    }
}

@Composable
fun SongListLayout(uiState: SongListPageState, onRetry: () -> Unit) {
    Column {
        Text(
            text = stringResource(id = R.string.song_list_title_txt),
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 36.dp, 16.dp)
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when (uiState.songList) {
                ApiResponse.Loading -> SongListLoadingLayout()
                is ApiResponse.Success -> SongListSuccessLayout(uiState.songList.value)
                is ApiResponse.Error -> SongListErrorLayout(stringResource(id = R.string.song_list_error_message_txt)) {
                    onRetry.invoke()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SongListLayoutPreview() {
    SongListLayout(SongListPageState(ApiResponse.Loading)) {}
}