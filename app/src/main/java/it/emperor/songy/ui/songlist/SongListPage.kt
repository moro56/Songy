package it.emperor.songy.ui.songlist

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
import androidx.navigation.NavHostController
import it.emperor.songy.R
import it.emperor.songy.data.network.models.ApiResponse
import it.emperor.songy.navigation.NavDestination
import it.emperor.songy.navigation.destination
import it.emperor.songy.ui.songlist.state.SongListPageState
import it.emperor.songy.ui.songlist.views.SongListErrorLayout
import it.emperor.songy.ui.songlist.views.SongListLoadingLayout
import it.emperor.songy.ui.songlist.views.SongListSuccessLayout
import org.koin.androidx.compose.getViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SongListPage(navController: NavHostController) {
    val viewModel = getViewModel<SongListPageViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.loadSongs()
    }

    SongListLayout(
        uiState,
        onRetry = {
            viewModel.loadSongs()
        },
        onClick = {
            navController.navigate(
                NavDestination.SONG_DETAILS.destination(
                    URLEncoder.encode(
                        it,
                        StandardCharsets.UTF_8.toString()
                    )
                )
            )
        },
        onShowMore = {
            viewModel.showMoreSongs()
        }
    )
}

@Composable
fun SongListLayout(
    uiState: SongListPageState,
    onRetry: () -> Unit,
    onClick: (String) -> Unit,
    onShowMore: () -> Unit
) {
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
                is ApiResponse.Success -> SongListSuccessLayout(
                    uiState.currentSongs,
                    uiState.canShowMore,
                    onClick,
                    onShowMore
                )
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
    SongListLayout(SongListPageState(ApiResponse.Loading), {}, {}, {})
}