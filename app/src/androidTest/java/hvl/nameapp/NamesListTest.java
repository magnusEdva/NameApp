package hvl.nameapp;

import android.graphics.Bitmap;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by zorri on 30/01/2018.
 */
@RunWith(AndroidJUnit4.class)
public class NamesListTest {

    @Rule
    public final ActivityTestRule<NamesList> testActivity
            = new ActivityTestRule<>(NamesList.class);

    PersonManager p;

    @Before
    public void setup(){
        NameApp context = (NameApp) testActivity.getActivity().getApplicationContext();

        p = context.getStudents();
    }

    @Test
    public void checkList(){
        List<PersonDataModel> persons = p.getPersons();
        ImageView image = (ImageView)
                testActivity.getActivity().findViewById(R.id.profilePicture_namelist);
        for(PersonDataModel p : persons) {
            onData(allOf(is(instanceOf(String.class)), is(p.getName()))).perform(click());
            assertEquals(image.getTag(), p.getName());
        }
    }

    @Test
    public void testLongClick(){

    }
}
