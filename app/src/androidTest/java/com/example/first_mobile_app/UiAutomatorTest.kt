package com.example.first_mobile_app

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiAutomatorTest {

    @Test
    fun testStartActivityExplicitly() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val device = UiDevice.getInstance(instrumentation)

        // Go home if elsewhere
        device.pressHome()

        // Wait to make sure the device has time
        val launcherPackage = device.launcherPackageName
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            5000
        )

        // Explicitly define the Intent for MainActivity with FLAG_ACTIVITY_NEW_TASK
        val intent = Intent().apply {
            setClassName("com.example.first_mobile_app", "com.example.first_mobile_app.MainActivity")
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        // Launch MainActivity
        instrumentation.context.startActivity(intent)

        // Wait for the app
        device.wait(
            Until.hasObject(By.pkg("com.example.first_mobile_app").depth(0)),
            5000
        )

        // Click on the “start activity explicitly” button
        val startButton = device.findObject(UiSelector().text("Start Activity Explicitly"))
        assertTrue("Start Activity button not found", startButton.exists())
        startButton.click()

        // Wait for the second activity
        device.wait(
            Until.hasObject(By.pkg("com.example.first_mobile_app").depth(0)),
            5000
        )

        // Check for text of a challenge
        val challengeText = device.findObject(UiSelector().textContains("3. Rapid Changes"))
        assertTrue("Challenge text not found", challengeText.exists())
    }
}
