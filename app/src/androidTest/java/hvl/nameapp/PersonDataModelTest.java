package hvl.nameapp;

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

import static junit.framework.Assert.assertTrue;

/**
 * Created by zorri on 30/01/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PersonDataModelTest {

    PersonDataModel p;
    NameApp mContext;
    Bitmap b;
    String name = "Test";

    @Rule
    public ActivityTestRule<HomeScreen> mActivity =
            new ActivityTestRule<>(HomeScreen.class);
    @Before
    public void reset(){
        if(mContext == null)
            mContext =(NameApp) mActivity.getActivity().getApplicationContext();
        mContext.setPersonDataModelFilePath();

        b = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.elmo);
        p = new PersonDataModel(name, b);
    }

    @After
    public void removeGarbage(){
        p.deletePicture();
        p = null;
    }

    @Test
    public void createsFile() {
        File file = new File(PersonDataModel.dir + "/" + p.getPicture());
        assertTrue(file.exists());
    }

    @Test
    public void deletePicture(){
        assertTrue(p.deletePicture());
        File file = new File(PersonDataModel.dir + "/" + p.getPicture());
        assertTrue(!file.exists());
    }

    @Test
    public void sameName(){
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.panda);
        PersonDataModel test = new PersonDataModel(name, bitmap);


        assertTrue(!p.getPicture().equals(test.getPicture()));
        assertTrue(!p.getPictureAsBitmap().sameAs(test.getPictureAsBitmap()));
    }


}