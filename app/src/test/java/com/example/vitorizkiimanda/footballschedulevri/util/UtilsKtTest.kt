package com.example.vitorizkiimanda.footballschedulevri.util

import com.dicoding.kotlinacademy.util.formatDate
import org.junit.Test

import org.junit.Assert.*

class UtilsKtTest {

    @Test
    fun formatDate() {
        val date = ("2018-12-10")
        assertEquals("Mon, 10 Dec 2018", formatDate(date))
    }
}