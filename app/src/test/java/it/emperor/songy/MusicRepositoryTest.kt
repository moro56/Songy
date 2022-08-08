package it.emperor.songy

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import it.emperor.songy.data.models.SongsFeed
import it.emperor.songy.data.models.SongsFeedResponse
import it.emperor.songy.data.network.api.MusicApi
import it.emperor.songy.domain.music.IMusicRepository
import it.emperor.songy.domain.music.MusicRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response

class MusicRepositoryTest {

    private lateinit var sut: IMusicRepository

    @BeforeEach
    fun before() {
        sut = MusicRepository(apiClient)
    }

    @AfterEach
    fun after() {
        unmockkAll()
    }

    @Test
    fun test_getSongList() = runBlocking {
        coEvery { apiClient.getSongList(any()) } returns Response.success(
            SongsFeedResponse(
                SongsFeed(listOf(mockk(), mockk()))
            )
        )

        val result = sut.getSongList(60)

        Assertions.assertEquals(2, result?.size)
    }

    @Test
    fun test_getSongList_ko() = runBlocking {
        coEvery { apiClient.getSongList(any()) } returns Response.error(400, mockk {
            every { contentType() } returns null
            every { contentLength() } returns 0
        })

        val result = sut.getSongList(60)

        Assertions.assertNull(result)
    }

    /** MOCKS */

    private val apiClient = mockk<MusicApi>()
}