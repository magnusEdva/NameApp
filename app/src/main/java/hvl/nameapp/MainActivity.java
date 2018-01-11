package hvl.nameapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Set;
import java.util.HashSet;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dummyInitiate();


        setContentView(R.layout.activity_main);
    }


    public void dummyInitiate(){
        Student Magnus = new Student("Magnus Edvardsen", "@mipmap/magnus");
        Student Steffen = new Student("Steffen","@drawable/steffen");
        Student Kolbein = new Student("Kolbein Horeson Foldøy","@drawable/kolbein");

        Set<String> students = new HashSet<String>();

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet("students", students);

        editor.commit();
    }
}
