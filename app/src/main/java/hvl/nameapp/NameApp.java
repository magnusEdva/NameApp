package hvl.nameapp;

import android.app.Application;

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
    }

    public ArrayList<PersonDataModel> getStudents() {
        return persons.getPersons();
    }

    public void addStudent(PersonDataModel s) {persons.addPerson(s);}


}


