package info.sanaebadi.tddandroidunittest.model.util

import info.sanaebadi.tddandroidunittest.util.DateUtil
import info.sanaebadi.tddandroidunittest.util.DateUtil.GET_MONTH_ERROR
import info.sanaebadi.tddandroidunittest.util.DateUtil.monthNumbers
import info.sanaebadi.tddandroidunittest.util.DateUtil.months
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.TestReporter
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.random.Random

class DateUtilTest {

    companion object {
        const val TODAY = "04-2020"
    }

    @Test
    internal fun testGetCurrentTimestamp_returnTimestamp() {
        assertDoesNotThrow(object : Executable {
            override fun execute() {
                assertEquals(TODAY, DateUtil.currentTimeStamp)
                print("Timestamp generated correctly $TODAY")
            }

        })
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    internal fun getMonthFromNumber_returnSuccess(
        monthNumber: Int,
        testInfo: TestInfo,
        testReporter: TestReporter
    ) {

        assertEquals(months[monthNumber], DateUtil.getMonthFromNumber(monthNumbers[monthNumber]))
        print("${monthNumbers[monthNumber]} and ${months[monthNumber]}")
    }


    @ParameterizedTest
    @ValueSource(ints = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    internal fun getMonthFromNumber_returnError(
        monthNumber: Int,
        testInfo: TestInfo,
        testReporter: TestReporter
    ) {
        val randomInt = Random.nextInt(90) + 13

        assertEquals(
            DateUtil.getMonthFromNumber((randomInt * monthNumber).toString()), GET_MONTH_ERROR
        )
        print("${monthNumbers[monthNumber]} and $GET_MONTH_ERROR")
    }

}