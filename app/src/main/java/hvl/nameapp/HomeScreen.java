package hvl.nameapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    private Button showNamesBtn;
    private Button showImagesBtn;
    private Button learningmodeBtn;
    private ImageButton addUserBtn;
    ArrayList<StudentDataModel> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Initiate dummy data
        onInit();

        //Linking Elements in the layout to Java code.
        showNamesBtn = (Button) findViewById(R.id.visNavn);
        showImagesBtn = (Button) findViewById(R.id.visBilder);
        learningmodeBtn = (Button) findViewById(R.id.learningbtn);
        addUserBtn = (ImageButton) findViewById(R.id.addUserBtn);

        //OnClickListener "Vis Navn button"
        showNamesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectToNameList = new Intent(HomeScreen.this, NamesList.class );
                redirectToNameList.putExtra(getString(R.string.students), students);
                startActivity(redirectToNameList);
            }
        });
        //OnClickListener "Vis Bilder button"
        showImagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectToImagesList = new Intent(HomeScreen.this, ImagesList.class);
                redirectToImagesList.putExtra(getString(R.string.students), students);
                startActivity(redirectToImagesList);
            }
        });

        learningmodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent redirectToLearningGame = new Intent(HomeScreen.this, LearningActivity.class);
                redirectToLearningGame.putExtra(getString(R.string.students), students);
                startActivity(redirectToLearningGame);
            }
        });

        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirectToAddNew = new Intent(HomeScreen.this, AddNewStudent.class);
                startActivity(redirectToAddNew);
            }
        });
    }

    // Sets dummy data.
    private void onInit(){
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.elmo);
        StudentDataModel magnus = new StudentDataModel("Magnus", b);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.zoidberg);
        StudentDataModel steffen = new StudentDataModel("Steffen", b);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.panda);
        StudentDataModel kolbein = new StudentDataModel("Kolbein", b);
        students.add(magnus);
        students.add(steffen);
        students.add(kolbein);
    }
}
