package hvl.nameapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ImagesList extends Activity {

    PersonManager students;
    ImageAdapter adapter;
    GridView pictureGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_list);


        // Retrieves intent data (Arraylist of persons) from application Context.
        NameApp appContext = (NameApp) getApplicationContext();
        students = appContext.getStudents();

        pictureGrid = (GridView) findViewById(R.id.imageList_PictureGrid);

        //Param: App Context, Layout that contains a TextView for each string in array, String array.
        adapter = new ImageAdapter(this, students.getAllPicturesAsBitmaps());
        pictureGrid.setAdapter(adapter);

        pictureGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView name = (TextView) findViewById(R.id.imageList_nameTextView);
                name.setText(students.getPerson((int)id).getName());
                name.setVisibility(View.VISIBLE);
            }
        });

        // Sets the text (name) invisible after the user clicks on it.
        TextView name = (TextView) findViewById(R.id.imageList_nameTextView);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = (TextView) findViewById(R.id.imageList_nameTextView);
                name.setVisibility(View.INVISIBLE);
            }
        });
    }

}

