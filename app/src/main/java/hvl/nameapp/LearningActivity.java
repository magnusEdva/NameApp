package hvl.nameapp;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LearningActivity extends AppCompatActivity {

    PersonManager students;
    ImageView image;
    EditText text;
    Button compareButton;
    String correctName;

    Random r;

    int StudentId;
    int hiScore;
    int mistakes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        students = getStudents();
        r = new Random();
        createView();
        setupComparison();
        hiScore = 0;
        mistakes = 0;
        changeStudent();

    }
    /*
    generates the final hiscore toast
     */
    @Override
    protected  void onDestroy(){
        Toast.makeText(LearningActivity.this, getString(R.string.showScore) + hiScore, Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    //fetches arraylist with persons from Context.
    private PersonManager getStudents(){
        NameApp na = (NameApp) getApplicationContext();
        return na.getStudents();
    }
    /*
    loads the view resources from the View
     */
    private void createView(){
        image = (ImageView) findViewById(R.id.LearningGameImage);
        text = (EditText) findViewById(R.id.LearningGameText);
        compareButton = (Button) findViewById(R.id.LearningGameCompare);
    }

    private void setupComparison(){
       //enables the compareButton
        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer();
            }
        });
        //lets the user use the enter key on the keyboard instead of the button.
        text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    checkAnswer();
                }
                return false;
            }
        });
    }
    /*
    responsible for changing all necesary values for changing the student.
     */
    private void changeStudent(){
        StudentId = generateStudentId();
        correctName = students.getPerson(StudentId).getName();
        changeImage();
        text.getText().clear();
    }

    /*
    return a new id that is different from the one contained in StudentId
     */
    private int generateStudentId(){
        int temp = r.nextInt(students.getSize());
        while(temp == StudentId)
            temp = r.nextInt(students.getSize());
        return temp;
    }

    /*
    Responsible for checking answer and increasing Hiscore if applicable.
    also calls to change the student afterwards.
     */
    private void checkAnswer(){
        String comparer = text.getText().toString();
        if(comparer.equals(correctName)){
            hiScore++;
            Toast.makeText(LearningActivity.this, getString(R.string.correctGuess) + hiScore, Toast.LENGTH_SHORT).show();
        }
        else{
            mistakes++;
            Toast.makeText(LearningActivity.this, getString(R.string.wrongGuess) + correctName + " Feil: " + mistakes
                    , Toast.LENGTH_SHORT).show();
        }
        changeStudent();
    }

    /*
    Transition animation between images
     */
    public void changeImage() {
        //Create a bitmap drawable from the image to be shown
        Drawable bitmapDrawable = new BitmapDrawable(getResources(), students.getPerson(StudentId).getPictureAsBitmap());

        Drawable[] layers = new Drawable[] {
                image.getDrawable(), //Current image
                bitmapDrawable // Next image
        };

        TransitionDrawable transitionDrawable = new TransitionDrawable(layers);

        image.setImageDrawable(transitionDrawable);
        image.setTag(this.correctName);
        int durationMillis = 500;
        transitionDrawable.startTransition(durationMillis);
    }

    public String getImageTag(){
        return (String) image.getTag();
    }
}
