package io.github.rybalkinsd.kohttp.dsl.async

import io.github.rybalkinsd.kohttp.dsl.httpHead
import io.github.rybalkinsd.kohttp.util.json
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * @author sergey
 */
class HttpHeadAsyncDslTest {

    @Test
    fun `single sync http head invocation with param`() {
        val response = httpHeadAsync {
            host = "yandex.ru"
            path = "/search"
            port = 80
        }
        Assert.assertFalse("After coroutine call, we must have not-ready response", response.isCompleted)

        runBlocking {
            response.await().use {
                assertEquals(200, it.code())
            }
        }
    }
}