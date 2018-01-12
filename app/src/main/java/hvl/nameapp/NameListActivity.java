package hvl.nameapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.NameList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.Attributes;

public class NameListActivity extends AppCompatActivity {
    HashSet<Button> buttons;
    GridView grid;
    ArrayAdapter<String> adapter;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);
        createStudents();
        createList();


    }

    public void createList(){
        grid = (GridView) findViewById(R.id.gridview);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getAllNames() );
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ImageView image = (ImageView) findViewById(R.id.imageView);
                int iid = getResources().getIdentifier(students.get((int)id).getPicture(), "mipmap", getPackageName());
                image.setImageResource(iid);
                image.setVisibility(View.VISIBLE);

            }
        });
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView i = (ImageView) findViewById(R.id.imageView);
                Log.e("flyForbud", "lol");
                i.setVisibility(View.GONE);
            }
        });
    }



    public void createStudents(){
        students = new ArrayList<Student>();
        Set<String> s = getStudents();
        Iterator<String> i = s.iterator();
        while(i.hasNext()){
            students.add(new Student(i.next()));
        }
    }
    public Set<String> getStudents(){
        SharedPreferences sharedPreferences = getSharedPreferences("Student_shared_data", MODE_PRIVATE);
        Set<String> students = sharedPreferences.getStringSet("students", null);
        return students;
    }

    public String[] getAllNames(){
        String[] names = new String[students.size()];
        for(int i = 0; i < names.length; i++){
            names[i] = students.get(i).getName();
        }
        return names;
    }
}
