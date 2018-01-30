package hvl.nameapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by zorri on 30/01/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PersonDataModelTest {

    PersonDataModel p;
    NameApp mContext;
    Bitmap b;
    @Rule
    public ActivityTestRule<HomeScreen> mActivity =
            new ActivityTestRule<>(HomeScreen.class);
    @Before
    public void reset(){
        if(mContext == null)
            mContext =(NameApp) mActivity.getActivity().getApplicationContext();
        mContext.setPersonDataModelFilePath();

        b = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.elmo);
        p = new PersonDataModel("Test", b);
    }

    @After
    public void removeGarbage(){
        p.deletePicture();
        p = null;
    }

    @Test
    public void createsFile() {
        File file = new File(PersonDataModel.dir + "/" + p.getPicture());
        assert file.exists();
    }

    @Test
    public void checkPicture(){
        assert (p.getPictureAsBitmap().equals(b));
    }

    @Test
    public void deletePicture(){
        File file = new File(PersonDataModel.dir + "/" + p.getPicture());
        assert (file.delete());
    }
}