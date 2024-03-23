package com.example.myapp

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.myapp", appContext.packageName)
    }
    @Test
    fun useAppContext2() {
        fun solveTask(a: Double, b: Double, c: Double) {
            val desc = (b * b) - (4 * (a * c))
            val reshenie1 = ((-b) + (-1 * Math.sqrt(desc))) / (2 * a)
            val reshenie2 = ((-b) + (+1 * Math.sqrt(desc))) / (2 * a)
            Log.d("asdasdasd", "reshenie1 - ${reshenie1}, reshenie2 - ${reshenie2}")
        }

        solveTask(1.0,1.0,1.0)

    }




}