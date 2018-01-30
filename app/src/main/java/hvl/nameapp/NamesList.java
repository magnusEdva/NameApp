package hvl.nameapp;

import android.app.ListActivity;
import android.arch.persistence.room.Dao;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class NamesList extends ListActivity {

    List<PersonDataModel> students;
    ArrayAdapter<String> adapter;
    List<String> names;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names_list);

        // Retrieves intent data (Arraylist of students) from application context.
        final NameApp appContext = (NameApp) getApplicationContext();
        students = appContext.getStudents();
        //Param: App Context, Layout that contains a TextView for each string in array, String array.
        names = getAllNames();
        profileImage = (ImageView) findViewById(R.id.profilePicture_namelist);
        adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1,names);

        //Sets the list.
        getListView().setAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // gets the ID (not index) of the image resource, and sets it on screen, also set the image to visible.
                // int resourcePath = getResources().getIdentifier(students.get((int) id).getPictureAsBitmap(),"drawable",getPackageName());
                profileImage.setImageBitmap(students.get((int) id).getPictureAsBitmap());
                profileImage.setVisibility(View.VISIBLE);
            }
        });

        // Delete person on long click
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                // students.get((int) id)
                NameApp context = (NameApp) getApplicationContext();
                context.removeStudent(students.get((int) id));
                names.remove((int) id);
                adapter.notifyDataSetChanged();
                profileImage.setVisibility(View.INVISIBLE);

                return true;
            }
        });


        // Sets OnClickListener to hide profile picture when user clicks the image.
        ImageView profileImage = (ImageView) findViewById(R.id.profilePicture_namelist);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView profileImage = (ImageView) findViewById(R.id.profilePicture_namelist);
                profileImage.setVisibility(View.INVISIBLE);
            }
        });
    }
    // Help method for creating a String array of names from an arraylist of StudentModelData objects.
    private List<String> getAllNames(){
        List<String> studentNames = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            studentNames.add(students.get(i).getName());
        }
        return  studentNames;
    }
}
