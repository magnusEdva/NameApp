package hvl.nameapp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddNewStudent extends AppCompatActivity {

    private Button addNewBtn;
    private Button cancelBtn;
    private ImageButton imageBtn;
    private Bitmap imageBitmap;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestPermissions(new String[]{Manifest.permission.CAMERA},
                10);
        setContentView(R.layout.activity_add_new_student);

        // Linking Elements in the layout to Java code.
        addNewBtn = (Button) findViewById(R.id.add_button);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        imageBtn = (ImageButton) findViewById(R.id.student_image);

        // OnClickListener "Add New Student" button
        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Ingen funksjonalitet ennå", Toast.LENGTH_SHORT).show();

                // Hente navn fra EditText
                EditText navnET = (EditText) findViewById(R.id.nameInput);
                String navnString = navnET.getText().toString();

                // Lage StudentDataModel-object?
                StudentDataModel student = new StudentDataModel(navnString, imageBitmap);
                Intent intent = getIntent();
                intent.putExtra("student", student);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // OnClickListener "Cancel" button
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // OnClickListener ImageView
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (camera.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(camera, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageBtn.setImageBitmap(imageBitmap);
        }
    }
}
