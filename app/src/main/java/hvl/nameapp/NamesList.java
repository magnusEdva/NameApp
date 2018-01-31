package hvl.nameapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NamesList extends ListActivity {

    PersonManager persons;
    ArrayAdapter<String> adapter;
    List<String> names;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names_list);

        // Retrieves context and casts it to custom NameApp context.
        // Needs to cast for access to custom methods.
        final NameApp appContext = (NameApp) getApplicationContext();
        persons = appContext.getStudents();

        //adds a list with strings for the arrayAdapter. Stored for usage with delete.
        names = persons.getAllNames();
        profileImage = (ImageView) findViewById(R.id.profilePicture_namelist);
        adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, names);

        //Sets the list.
        getListView().setAdapter(adapter);

        setUpListViewListener();
        setUpLongClickListener();
        setUpImageViewListener();


    }

    private void setUpListViewListener() {
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // gets the ID (not index) of the image resource, and sets it on screen, also set the image to visible.
                // int resourcePath = getResources().getIdentifier(persons.get((int) id).getPictureAsBitmap(),"drawable",getPackageName());
                profileImage.setImageBitmap(persons.getPerson((int) id).getPictureAsBitmap());
                profileImage.setVisibility(View.VISIBLE);
                profileImage.setTag(persons.getPerson((int) id).getName());
            }
        });

    }

    private void setUpLongClickListener() {
        // Delete person on long click
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                // persons.get((int) id)
                NameApp context = (NameApp) getApplicationContext();
                Toast.makeText(getApplicationContext(), names.get((int) id) + " ble slettet", Toast.LENGTH_SHORT).show();
                context.removeStudent(persons.getPerson((int) id));
                names.remove((int) id);
                adapter.notifyDataSetChanged();
                profileImage.setVisibility(View.INVISIBLE);

                return true;
            }
        });
    }

    private void setUpImageViewListener() {
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
}
