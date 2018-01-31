package hvl.nameapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by zorri on 30/01/2018.
 */
@RunWith(AndroidJUnit4.class)
public class NamesListTest {

    @Rule
    public final ActivityTestRule<NamesList> testActivity
            = new ActivityTestRule<>(NamesList.class);


}
