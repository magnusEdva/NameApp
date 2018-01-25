package hvl.nameapp;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by zorri on 23/01/2018.
 */

public class NameApp extends Application {
    private PersonManager persons;

    @Override
    public void onCreate() {
        super.onCreate();

        //supermegaimportantline - sets the directory persons finds their pictures in
        PersonDataModel.dir = this.getFilesDir().getPath();

        persons = new PersonManager(this);


        SharedPreferences sr = getSharedPreferences("preferences",MODE_PRIVATE);

        String owner = sr.getString("owner", null);

        if(owner == null){
            Intent intent = new Intent(this, AddNewStudent.class);
            intent.putExtra("makeOwner","new Owner");
            startActivity(intent);
        }

    }

    public ArrayList<PersonDataModel> getStudents() {
        return persons.getPersons();
    }

    public void addStudent(PersonDataModel s) {persons.addPerson(s);}


}


