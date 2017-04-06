package com.example.jean.retrofitexample;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.widget.Button;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends InstrumentationTestCase {
    private MockWebServer server;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Test
    public void testButton() {
        Button button = (Button) mActivityRule.getActivity().findViewById(R.id.button_retry);
        mActivityRule.getActivity().setButtonText();
        assertEquals("HAHAHA", button.getText().toString());
    }
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.jean.retrofitexample", appContext.getPackageName());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    /*    System.out.println("BEFORE RUN");
        server = new MockWebServer();
//        server.start();
        injectInstrumentation(getInstrumentation());
        QuoteOfTheDayConstants.BASE_URL = server.url("/").toString();*/
    }

    @Test
    public void testQuoteIsShown() throws Exception {
        String fileName = "quote_200_ok_response.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.button_retry)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withText("I came from a real tough neighborhood. Once a guy pulled a knife on me. I knew he wasn't a professional, the knife had butter on it.")).check(matches(isDisplayed()));
    }


    @Test
    public void testRetryButtonShowsWhenError() throws Exception {
        String fileName = "quote_404_not_found.json";

        server.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.button_retry)).check(matches(isDisplayed()));
        onView(withText("Quote Not found")).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
//        server.shutdown();
    }
}
