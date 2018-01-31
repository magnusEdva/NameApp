package hvl.nameapp;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by zorri on 30/01/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeScreenTest {

    @Rule
    public ActivityTestRule<HomeScreen> mActivity =
            new ActivityTestRule<>(HomeScreen.class);

    @Before
    public void setup(){
        Intents.init();
    }

    @After
    public void garbageCollection(){
        Intents.release();
    }

    @Test
    public void visNavn(){
        onView(withId(R.id.visNavn)).perform(click());

        intended(hasComponent(NamesList.class.getName()));


    }

    @Test
    public void visBilder(){
        onView(withId(R.id.visBilder)).perform(click());
        intended(hasComponent(ImagesList.class.getName()));
    }

    @Test
    public void visLearning(){

        onView(withId(R.id.learningbtn)).perform(click());
        intended(hasComponent(LearningActivity.class.getName()));
    }

    @Test
    public void addNew(){

        onView(withId(R.id.addUserBtn)).perform(click());
        intended(hasComponent(AddNewStudent.class.getName()));
    }

}
