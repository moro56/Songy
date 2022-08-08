package it.emperor.songy

import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import it.emperor.songy.data.models.Song
import it.emperor.songy.data.network.models.ApiResponse
import it.emperor.songy.domain.music.IMusicRepository
import it.emperor.songy.ui.songlist.SongListPageViewModel
import it.emperor.songy.ui.songlist.state.SongListPageState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.junit5.KoinTestExtension

@OptIn(ExperimentalCoroutinesApi::class)
class SongListPageViewModelTest : KoinTest {

    private val viewModel: SongListPageViewModel by inject()

    @JvmField
    @RegisterExtension
    val koinTestExtension = KoinTestExtension.create {
        modules(
            module {
                single { musicRepository }
                single { SongListPageViewModel(get()) }
            })
    }

    @BeforeEach
    fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterEach
    fun after() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    @Test
    fun test_loadSongs() = runTest(UnconfinedTestDispatcher()) {
        coEvery { musicRepository.getSongList(any()) } returns listOf(mockk(), mockk())
        val testResults = mutableListOf<SongListPageState>()
        val job = launch {
            viewModel.uiState.toList(testResults)
        }

        viewModel.loadSongs().join()

        Assertions.assertTrue(testResults.first().songList is ApiResponse.Loading)
        Assertions.assertTrue(testResults.last().songList is ApiResponse.Success<List<Song>>)
        Assertions.assertEquals(2, testResults.last().currentSongs.size)
        Assertions.assertEquals(1, testResults.last().currentPage)
        Assertions.assertEquals(3, testResults.last().maxPages)
        job.cancelAndJoin()
    }

    @Test
    fun test_loadSongs_ko() = runTest(UnconfinedTestDispatcher()) {
        coEvery { musicRepository.getSongList(any()) } returns null
        val testResults = mutableListOf<SongListPageState>()
        val job = launch {
            viewModel.uiState.toList(testResults)
        }

        viewModel.loadSongs().join()

        Assertions.assertTrue(testResults.first().songList is ApiResponse.Loading)
        Assertions.assertTrue(testResults.last().songList is ApiResponse.Error)
        Assertions.assertEquals(0, testResults.last().currentSongs.size)
        Assertions.assertEquals(1, testResults.last().currentPage)
        Assertions.assertEquals(3, testResults.last().maxPages)
        job.cancelAndJoin()
    }

    @Test
    fun test_showMoreSongs() = runTest(UnconfinedTestDispatcher()) {
        val mocks = (0..60).map { mockk<Song>() }
        coEvery { musicRepository.getSongList(any()) } returns mocks
        val testResults = mutableListOf<SongListPageState>()
        val job = launch {
            viewModel.uiState.toList(testResults)
        }
        viewModel.loadSongs().join()

        viewModel.showMoreSongs()
        delay(1200)

        Assertions.assertTrue(testResults.last().songList is ApiResponse.Success<List<Song>>)
        Assertions.assertEquals(40, testResults.last().currentSongs.size)
        Assertions.assertEquals(2, testResults.last().currentPage)
        Assertions.assertEquals(3, testResults.last().maxPages)

        viewModel.showMoreSongs()
        delay(1200)

        Assertions.assertEquals(60, testResults.last().currentSongs.size)
        Assertions.assertEquals(3, testResults.last().currentPage)
        Assertions.assertEquals(3, testResults.last().maxPages)

        viewModel.showMoreSongs()
        delay(1200)

        Assertions.assertEquals(60, testResults.last().currentSongs.size)
        Assertions.assertEquals(3, testResults.last().currentPage)
        Assertions.assertEquals(3, testResults.last().maxPages)
        job.cancelAndJoin()
    }

    /** MOCKS */

    private val musicRepository = mockk<IMusicRepository>()
}