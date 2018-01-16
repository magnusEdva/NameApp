package hvl.nameapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImagesList extends Activity {

    ArrayList<StudentDataModel> students = new ArrayList<>();
    ImageAdapter adapter;
    GridView pictureGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_list);


        // Retrieves intent data (Arraylist of students) from homeScreen class.
        Intent intent = getIntent();
        students = (ArrayList<StudentDataModel>) intent.getSerializableExtra(getString(R.string.students));

        pictureGrid = (GridView) findViewById(R.id.imageList_PictureGrid);

        //Param: App Context, Layout that contains a TextView for each string in array, String array.
        adapter = new ImageAdapter(this, getAllImages());
        pictureGrid.setAdapter(adapter);
        pictureGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView name = (TextView) findViewById(R.id.imageList_nameTextView);
                name.setText(students.get((int)id).getName());
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

    // Help method for creating a String array of names from an arraylist of StudentModelData objects.
    private ArrayList<Bitmap> getAllImages(){
        ArrayList<Bitmap> studentImages = new ArrayList<Bitmap>();
        for (int i = 0; i < students.size(); i++) {
             studentImages.add(students.get(i).getPicture());
        }
        return  studentImages;
    }
}

