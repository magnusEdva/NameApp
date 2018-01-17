package hvl.nameapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddNewStudent extends AppCompatActivity {

    private Button addNewBtn;
    private Button cancelBtn;
    private ImageButton imageBtn;
    private ImageButton galleryBtn;
    private Bitmap imageBitmap = null;
    private byte[] imageBytes = null;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GALLERY_REQUEST = 2;

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
        galleryBtn = (ImageButton) findViewById(R.id.student_image_folder);

        // OnClickListener "Add New Student" button
        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Hente navn fra EditText
                EditText navnET = (EditText) findViewById(R.id.nameInput);
                String navnString = navnET.getText().toString();



                if (navnString.length() >= 1 && (imageBitmap != null || imageBytes != null)) {

                    // Lage StudentDataModel-object?
                    StudentDataModel student;
                    if (imageBitmap != null) {
                        student = new StudentDataModel(navnString, imageBitmap);
                    } else {
                        student = new StudentDataModel(navnString, imageBytes);
                    }
                    Intent intent = getIntent();
                    intent.putExtra("student", student);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(getApplicationContext(), navnString + " er lagt til!", Toast.LENGTH_SHORT).show();
                } else if (navnString.length() < 1 && imageBitmap == null) {
                    Toast.makeText(getApplicationContext(), "Mangler bilde og navn", Toast.LENGTH_SHORT).show();
                } else if (navnString.length() < 1) {
                    Toast.makeText(getApplicationContext(), "Mangler navn", Toast.LENGTH_SHORT).show();
                } else if (imageBitmap == null) {
                    Toast.makeText(getApplicationContext(), "Mangler bilde", Toast.LENGTH_SHORT).show();
                }
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

        // OnClickListener ImageView Camera
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (camera.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(camera, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        // OnClickListener ImageView Folder
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicketIntent = new Intent(Intent.ACTION_PICK);
                photoPicketIntent.setType("image/*");
                startActivityForResult(photoPicketIntent, GALLERY_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageBtn.setImageBitmap(imageBitmap);
        } else if (requestCode == GALLERY_REQUEST) {
            Uri selectedImage = data.getData();
            try {
                Bitmap imageBitmapFromMedia = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Komprimerer bilde nokså hardt her
                imageBitmapFromMedia.compress(Bitmap.CompressFormat.JPEG, 20, stream);
                imageBytes = stream.toByteArray();
                galleryBtn.setImageBitmap(imageBitmapFromMedia);
            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
        }
    }
}
