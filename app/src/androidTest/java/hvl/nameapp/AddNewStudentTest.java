package hvl.nameapp;


import android.content.Intent;
import android.provider.MediaStore;
import android.support.test.espresso.intent.Intents;
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
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasType;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.EasyMock2Matchers.equalTo;


/**
 * Created by kfold on 30.01.2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddNewStudentTest {

    @Rule
    public ActivityTestRule<AddNewStudent> mActivity = new ActivityTestRule(AddNewStudent.class);

    @Before
    public void setup(){
        Intents.init();
    }

    @After
    public void garbageCollection(){
        Intents.release();
    }

//    @Test
//    public void useAppContext() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        assertEquals("hvl.nameapp", appContext.getPackageName());
//    }

    @Test
    public void addNewUser() {

        onView(withId(R.id.add_button)).perform(click());
        assertTrue(mActivity.getActivity().isFinishing());
    }

    @Test
    public void cancel() {

        onView(withId(R.id.cancel_btn)).perform(click());
        assertTrue(mActivity.getActivity().isFinishing());
    }

    @Test
    public void openCamera() {

        onView(withId(R.id.student_image)).perform(click());
        intended(hasAction(equalTo(MediaStore.ACTION_IMAGE_CAPTURE)));
    }

//    @Test
//    public void openGallery() {
//
//        onView(withId(R.id.student_image_folder)).perform(click());
//        intended(allOf(hasAction(equalTo(Intent.ACTION_PICK)),hasType(is("image/*"))));
//
//    }

}
