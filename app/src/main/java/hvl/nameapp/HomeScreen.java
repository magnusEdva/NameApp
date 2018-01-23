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
    static final int NEW_USER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
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
                startActivity(redirectToNameList);
            }
        });
        //OnClickListener "Vis Bilder button"
        showImagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectToImagesList = new Intent(HomeScreen.this, ImagesList.class);
                startActivity(redirectToImagesList);
            }
        });

        learningmodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent redirectToLearningGame = new Intent(HomeScreen.this, LearningActivity.class);
                startActivity(redirectToLearningGame);
            }
        });

        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirectToAddNew = new Intent(HomeScreen.this, AddNewStudent.class);
                startActivityForResult(redirectToAddNew, NEW_USER_REQUEST);
            }
        });
    }

    // Sets dummy data.
    private void onInit() {
        StudentDataModel.dir = getFilesDir();
        NameApp na = (NameApp) getApplicationContext();

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.elmo);
        StudentDataModel magnus = new StudentDataModel("Magnus", b);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.zoidberg);
        StudentDataModel steffen = new StudentDataModel("Steffen", b);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.panda);
        StudentDataModel kolbein = new StudentDataModel("Kolbein", b);

        na.addStudent(magnus);
        na.addStudent(steffen);
        na.addStudent(kolbein);
    }
}
