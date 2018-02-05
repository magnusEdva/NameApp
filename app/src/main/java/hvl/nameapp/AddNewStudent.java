package hvl.nameapp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class AddNewStudent extends AppCompatActivity {

    private Button addNewBtn;
    private Button cancelBtn;
    private ImageButton imageBtn;
    private ImageButton galleryBtn;
    private Bitmap imageBitmap;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GALLERY_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageBitmap = null;
        requestPermissions(new String[]{Manifest.permission.CAMERA},
                10);
        setContentView(R.layout.activity_add_new_student);


        // Linking Elements in the layout to Java code.
        getViews();

        boolean generateOwner = false;
        String owner = getIntent().getStringExtra("makeOwner");
        if(owner != null){
            addNewBtn.setText("Opprett profil");
            generateOwner = true;
        }


        // OnClickListener "Add New Student" button
        buttonFunctionality(generateOwner);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
                imageBtn.setImageBitmap(imageBitmap);
            } else if (requestCode == GALLERY_REQUEST) {
                Uri selectedImage = data.getData();
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 20, stream);
//                    byte[] byteArray = stream.toByteArray();
//                    imageBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
                    imageBitmap = getResizedBitmap(imageBitmap, 400, 400);
                    galleryBtn.setImageBitmap(imageBitmap);
                } catch (IOException e) {
                    Log.i("TAG", "Some exception " + e);
                }
            }
        }
    }

    public void getViews() {
        addNewBtn = (Button) findViewById(R.id.add_button);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        imageBtn = (ImageButton) findViewById(R.id.student_image);
        galleryBtn = (ImageButton) findViewById(R.id.student_image_folder);
    }

    public void buttonFunctionality(final boolean generateOwner) {
        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Hente navn fra EditText
                EditText navnET = (EditText) findViewById(R.id.nameInput);
                String navnString = navnET.getText().toString();


                if (navnString.length() >= 1 && (imageBitmap != null)) {

                    // Lage PersonDataModel-object?
                    PersonDataModel student;
                    student = new PersonDataModel(navnString, imageBitmap);
                    NameApp context = (NameApp) getApplicationContext();
                    if(generateOwner) {
                        student.setName(student.getName() + " (owner)");
                        context.addOwner(student);
                    } else {
                        context.addStudent(student);
                    }

                    Toast.makeText(getApplicationContext(), navnString + getString(R.string.isAdded), Toast.LENGTH_SHORT).show();
                } else if (navnString.length() < 1 && imageBitmap == null) {
                    Toast.makeText(getApplicationContext(), R.string.missingNameAndImage, Toast.LENGTH_SHORT).show();
                } else if (navnString.length() < 1) {
                    Toast.makeText(getApplicationContext(), R.string.missingName, Toast.LENGTH_SHORT).show();
                } else if (imageBitmap == null) {
                    Toast.makeText(getApplicationContext(), R.string.missingImage, Toast.LENGTH_SHORT).show();
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

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


}
