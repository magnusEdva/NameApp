package hvl.nameapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

/**
 * Created by zorri on 30/01/2018.
 */
@RunWith(AndroidJUnit4.class)
public class LearningActivityTest {

    @Rule
    public ActivityTestRule<LearningActivity> testActivity =
            new ActivityTestRule<>(LearningActivity.class);

    @Test
    public void correctGuess() {
        int HighScore = testActivity.getActivity().hiScore;
        int mistakes = testActivity.getActivity().mistakes;
        String name = testActivity.getActivity().correctName;

        guess(name);

        assertTrue((HighScore + 1) == testActivity.getActivity().hiScore);
        assertTrue(mistakes == testActivity.getActivity().mistakes);
    }

    @Test
    public void wrongGuess() {
        int HighScore = testActivity.getActivity().hiScore;
        int mistakes = testActivity.getActivity().mistakes;


        guess("");

        assertTrue((mistakes + 1) == testActivity.getActivity().mistakes);
        assertTrue(HighScore == testActivity.getActivity().hiScore);
    }

    @Test
    public void assertCorrectImage() {
        //check tag correspons with iamge
        String tag =  testActivity.getActivity().getImageTag();
        assertTrue(checkImageTag(tag));
        //make image udate
        guess("");
        //check again if tag corresponds correctly
        String tagTwo =  testActivity.getActivity().getImageTag();
        assertTrue(checkImageTag(tagTwo));
        assertTrue(!tag.equals(tagTwo));
    }

    public void guess(String name){
        onView(withId(R.id.LearningGameText)).perform(replaceText(name));
        onView(withId(R.id.LearningGameCompare)).perform(click());
    }

    public boolean checkImageTag(String tag){
        return testActivity.getActivity().correctName.equals(tag);
    }
}
