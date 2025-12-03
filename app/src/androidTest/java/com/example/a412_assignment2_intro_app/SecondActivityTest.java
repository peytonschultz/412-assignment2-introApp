/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.a412_assignment2_intro_app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.widget.Toast;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import androidx.test.core.app.ActivityScenario;
import androidx.test.filters.SdkSuppress;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

/**
 * Basic sample for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class SecondActivityTest {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.example.a412_assignment_intro_app";

    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    private UiDevice mDevice;

    /**
     * Copied from the github linked here
     * Intention is to start up the main activity before any test cases are ran
     */
    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        //swipe upwards to view drawer
        mDevice.swipe(500, 1500, 500, 200, 10);

        //waits for app to be installed
        mDevice.wait(Until.hasObject(By.pkg(mDevice.getLauncherPackageName()).depth(0)), 5000);

        String appName = "412-assignment2-intro-app";

        // Launch app from the home screen
        UiObject2 appIcon = mDevice.wait(Until.findObject(By.text(appName)), 5000);

        // Click the icon
        appIcon.click();

        //wait for app to appear
        mDevice.wait(Until.hasObject(By.pkg("com.example.a412-assignment2-intro-app").depth(0)), 5000);
    }

    @Test
    public void test_secondActivityActivation() {

        //clicks the button in the app
        mDevice.findObject(By.text("Start Activity Explicitly!")).click();

        //waits until the linear layour containing the challenges have loaded
        mDevice.wait(Until.hasObject(By.res("com.example.412-assignment2-intro-app:id/ll_challenges")), 5000);

        UiObject2 nameObj = mDevice.findObject(By.textContains("Device Fragmentation"));

        UiObject2 parent = nameObj.getParent(); //gets parent element (linear layout)
        List<UiObject2> children = parent.getChildren();
        UiObject2 descObj = null;
        //assigns the description (TextView) to descObj
        for (UiObject2 child : children) {
            if (!child.equals(nameObj) && child.getClassName().equals("android.widget.TextView")) {
                descObj = child;
                break;
            }
        }

        //asserts that descObj is not null, Device Fragmentation is the only text in nameObj, and that the correct description exists for this mobile software challenge
        assertNotNull(descObj);
        assertEquals("Device Fragmentation", nameObj.getText());
        assertEquals(
                "There are a large varieties of different device configurations. (Screen size, refresh rates, etc.)",
                descObj.getText());

    }

}