package com.livefast.eattrash.raccoonforfriendica.domain.urlhandler.processor

import com.livefast.eattrash.raccoonforfriendica.core.navigation.DetailOpener
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.TimelineEntryModel
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultEntryProcessorTest {
    private val detailOpener = mock<DetailOpener>(mode = MockMode.autoUnit)
    private val fetchEntry = mock<FetchEntryUseCase>()
    private val sut =
        DefaultEntryProcessor(
            detailOpener = detailOpener,
            fetchEntry = fetchEntry,
        )

    @Test
    fun `given valid user when process URL then interactions are as expected`() =
        runTest {
            val entry = TimelineEntryModel(id = "0", content = "")
            everySuspend { fetchEntry.invoke(any()) } returns entry
            val url = "https://$HOST/display/$ENTRY_ID"

            val res = sut.process(url)

            assertTrue(res)
            verifySuspend {
                fetchEntry.invoke(url)
            }
            verify {
                detailOpener.openEntryDetail(entry)
            }
        }

    @Test
    fun `given user not found when process URL then interactions are as expected`() =
        runTest {
            everySuspend { fetchEntry.invoke(any()) } returns null
            val url = "https://$HOST/display/$ENTRY_ID"

            val res = sut.process(url)

            assertFalse(res)
            verifySuspend {
                fetchEntry.invoke(url)
            }
            verify(mode = VerifyMode.not) {
                detailOpener.openEntryDetail(any())
            }
        }

    @Test
    fun `given invalid URL when process URL then interactions are as expected`() =
        runTest {
            everySuspend { fetchEntry.invoke(any()) } returns null
            val url = "https://$HOST/p/$ENTRY_ID"

            val res = sut.process(url)

            assertFalse(res)
            verifySuspend {
                fetchEntry.invoke(any())
            }
            verify(mode = VerifyMode.not) {
                detailOpener.openEntryDetail(any())
            }
        }

    companion object {
        private const val HOST = "example.com"
        private const val ENTRY_ID = "objectId"
    }
}