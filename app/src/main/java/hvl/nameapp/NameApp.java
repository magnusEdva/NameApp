package hvl.nameapp;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zorri on 23/01/2018.
 */

public class NameApp extends Application {
    private PersonManager persons;

    @Override
    public void onCreate() {
        super.onCreate();

        //supermegaimportantline - sets the directory persons finds their pictures in
        setPersonDataModelFilePath();

        persons = new PersonManager(this);


        SharedPreferences sr = getSharedPreferences("preferences",MODE_PRIVATE);

        String owner = sr.getString("owner", null);

        if(owner == null){
            Intent intent = new Intent(this, AddNewStudent.class);
            intent.putExtra("makeOwner","new Owner");
            startActivity(intent);
        }

    }

    public final PersonManager getStudents() {
        return persons;
    }

    public void addStudent(PersonDataModel s) {persons.addPerson(s);}


    public void addOwner(PersonDataModel student) {
        persons.addPerson(student);
        SharedPreferences sp = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("owner", student.getName());
        edit.commit();
    }

    public void removeStudent(PersonDataModel student) {
        SharedPreferences sp = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        // dersom person er owner
        String owner = sp.getString("owner", null);
        if (owner.equals(student.getName())) {
            edit.remove(owner);
        }
        persons.deletePerson(student);
    }

    public void setPersonDataModelFilePath(){
        PersonDataModel.dir = this.getFilesDir().getPath();
    }
}


