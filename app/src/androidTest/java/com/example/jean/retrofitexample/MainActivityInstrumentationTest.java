package com.example.jean.retrofitexample;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import junit.framework.TestCase;

/**
 * Created by rks on 4/6/17.
 */

public class MainActivityInstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private Button button;

    public MainActivityInstrumentationTest() {
        super(MainActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        button = (Button) mainActivity.findViewById(R.id.button_retry);
    }

    public void testPreconditions() {
        assertNotNull("mTestActivity is null", mainActivity);
    }

    public void testButton() {
        mainActivity.setButtonText();
        assertEquals("HAHAHA", button.getText().toString());
    }
}
