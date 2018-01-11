package hvl.nameapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.HashSet;
import java.util.Set;

public class NameListActivity extends AppCompatActivity {
    Set<String> students;
    HashSet<Button> buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);
        students = getStudents();
        buttons = new HashSet<Button>();


    }

    public Set<String> getStudents(){
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        Set<String> students = sharedPref.getStringSet("students", null);
        return students;
    }


    public void createList(){
        GridView grid = new GridView(this);

        for(int i = 0; i < students.size(); i++){
            buttons.add(new Button(this));

        }
    }
}
