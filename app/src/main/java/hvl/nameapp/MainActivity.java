package hvl.nameapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.HashSet;
import java.util.Set;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dummyInitiate();


        setContentView(R.layout.activity_main);
    }


    public void dummyInitiate(){
        Student Magnus = new Student("Magnus Edvardsen", "hvl.nameapp:mipmap/magnus");
        Student Steffen = new Student("Steffen","hvl.nameapp:drawable/steffen");
        Student Kolbein = new Student("Kolbein Horeson Foldøy","hvl.nameapp:drawable/kolbein");

        Set<String> students = new HashSet<String>();
        students.add(Magnus.toString());
        students.add(Steffen.toString());
        students.add(Kolbein.toString());

        SharedPreferences sharedPref = getSharedPreferences("Student_shared_data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet("students", students);
        editor.apply();
    }

    public void openNameList(View view){
        Intent intent = new Intent(this, NameListActivity.class);
        startActivity(intent);
    }
}
