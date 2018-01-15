package hvl.nameapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class LearningActivity extends AppCompatActivity {

    ArrayList<StudentDataModel> students;
    ImageView image;
    EditText text;
    Button compareButton;
    String correctName;
    int StudentId;
    Random r;
    int hiScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        students = getStudents();
        r = new Random();
        createView();
        setupComparison();
        hiScore = 0;
        changeStudent();


    }
    @Override
    protected  void onDestroy(){
        Toast.makeText(LearningActivity.this, getString(R.string.showScore) + hiScore, Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    //fetches arraylist with students from Intent.
    private ArrayList<StudentDataModel> getStudents(){
        return (ArrayList<StudentDataModel>) getIntent().getSerializableExtra(getString(R.string.students));
    }

    private void createView(){
        image = (ImageView) findViewById(R.id.LearningGameImage);
        text = (EditText) findViewById(R.id.LearningGameText);
        compareButton = (Button) findViewById(R.id.LearningGameCompare);
    }

    private void setupComparison(){
        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer();
            }
        });
        text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    checkAnswer();
                }
                return false;
            }
        });
    }

    private void changeStudent(){
        StudentId = generateStudentId();
        image.setImageBitmap(students.get(StudentId).getPicture());
        correctName = students.get(StudentId).getName();
        text.getText().clear();
    }
    /*
    return a new id that is different from the one contained in StudentId
     */
    private int generateStudentId(){
        int temp = r.nextInt(students.size());
        while(temp == StudentId)
            temp = r.nextInt(students.size());
        return temp;
    }


    private void checkAnswer(){
        String comparer = text.getText().toString();
        if(comparer.equals(correctName)){
            hiScore++;
            Toast.makeText(LearningActivity.this, getString(R.string.correctGuess) + hiScore, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(LearningActivity.this, getString(R.string.wrongGuess) + correctName, Toast.LENGTH_SHORT).show();
        }
        changeStudent();
    }
}
