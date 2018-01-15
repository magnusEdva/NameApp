package hvl.nameapp;

import android.content.Intent;
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
                redirectToNameList.putExtra("students", students);
                startActivity(redirectToNameList);
            }
        });
        //OnClickListener "Vis Bilder button"
        showImagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirectToImagesList = new Intent(HomeScreen.this, ImagesList.class);
                redirectToImagesList.putExtra("students", students);
                startActivity(redirectToImagesList);
            }
        });
    }

    // Sets dummy data.
    private void onInit(){
        StudentDataModel magnus = new StudentDataModel("Magnus Edvardsen", "elmo");
        StudentDataModel steffen = new StudentDataModel("Steffen Andre Hagen", "zoidberg");
        StudentDataModel kolbein = new StudentDataModel("Kolbein Toresson Foldøy", "panda");
        students.add(magnus);
        students.add(steffen);
        students.add(kolbein);
    }
}
