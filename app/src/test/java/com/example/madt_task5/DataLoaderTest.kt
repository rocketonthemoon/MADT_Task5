package com.example.madt_task5

import org.junit.Assert.assertFalse
import org.junit.Test

class DataLoaderTest {
    @Test
    fun testDoInBackground() {
        val dataLoader = DataLoader(MainActivity())
        val result = dataLoader.doInBackground()

        // Check that the result is not empty
        assertFalse(result.isEmpty())
    }
}