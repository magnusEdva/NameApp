package hvl.nameapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class LearningActivity extends AppCompatActivity {

    ArrayList<StudentDataModel> students;
    ImageView image = (ImageView) findViewById(R.id.LearningGameImage);
    EditText text = (EditText) findViewById(R.id.LearningGameText);
    Button compareButton = (Button) findViewById(R.id.LearningGameCompare);
    Random r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        students = getStudents();
        r = new Random();



    }
    //fetches arraylist with students from Intent.
    private ArrayList<StudentDataModel> getStudents(){
        return (ArrayList<StudentDataModel>) getIntent().getSerializableExtra("students");
    }
}
