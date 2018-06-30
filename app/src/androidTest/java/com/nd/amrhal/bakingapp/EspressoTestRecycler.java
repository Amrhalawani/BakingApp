package com.nd.amrhal.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class EspressoTestRecycler {
//
    /**
     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
     * activity. The activity that will be tested, MenuActivity in this case, will be launched
     * before each test that's annotated with @Test and before methods annotated with @Before.
     * <p>
     * The activity will be terminated after the test and methods annotated with @After are
     * complete. This rule allows you to directly access the activity during the test.
     */
    @Rule
    public ActivityTestRule<RecipesActivity> mActivityTestRule = new ActivityTestRule<>(RecipesActivity.class);

    private IdlingResource mIdlingResource;


    // TODO (6) Registers any resource that needs to be synchronized with Espresso before
    // the test is run.

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);

    }

    // TODO (7) Test that the gridView with Tea objects appears and we can click a gridView item
    @Test
    public void idlingResourceTest() {

        //splashScreen takes 4000ms to open our Recipes Activity
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(allOf(withId(R.id.item_recipe_name), withText("Nutella Pie"),
                childAtPosition(allOf(withId(R.id.constrainLayout),childAtPosition(withId(R.id.linearLayout),0)),0),  isDisplayed() ));

        // First, scroll to the position that needs to be matched and click on it.
            onView(ViewMatchers.withId(R.id.recycler_view))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                            click()));

//            // Match the text in an item below the fold and check that it's displayed.
//            String itemElementText = mActivityTestRule.getActivity().getResources()
//                    .getString(R.string.item_element_text)
//                    + String.valueOf(0);
//            onView(withText(itemElementText)).check(matches(isDisplayed()));
//

    }

    // TODO (8) Unregister resources when not needed to avoid malfunction
    @After
    public void unregisterIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        Espresso.unregisterIdlingResources(mIdlingResource);


    }

    //this for safe matcher
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
}}