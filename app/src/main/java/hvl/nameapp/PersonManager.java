package hvl.nameapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zorri on 23/01/2018.
 */

public class PersonManager {
    private List<PersonDataModel> persons;
    private static String FILE_PATH;
    private Context mContext;
    private static PersonDatabase database;

    public PersonManager(Context mContext) {
        this.mContext = mContext;
        this.FILE_PATH = mContext.getFilesDir() + "/" + mContext.getResources().getString(R.string.PersonRegister);
        database = Room.databaseBuilder(mContext.getApplicationContext(),
                PersonDatabase.class, "Persons.db")
                .build();
        loadFromDatabase();
    }


    //adds a person to the Java object
    public void addPerson(PersonDataModel p) {
        persons.add(p);
        insertPerson(p);
    }

    public List<PersonDataModel> getPersons() {
        return persons;
    }

    private void loadFromDatabase(){
        new  AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                  persons = database.personDAO().getAll();
                  if(persons.isEmpty())
                      onInit();
                  return null;
                }
            }.execute();
    }

    public void insertPerson(final PersonDataModel ... persons) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.personDAO().insertAll(persons);
                return null;
            }
        }.execute();
    }


    // Sets dummy data.
    private void onInit() {
        //don't run if not empty
        if (persons.isEmpty()) {
            NameApp na = (NameApp) mContext.getApplicationContext();

            Bitmap b = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.elmo);
            PersonDataModel magnus = new PersonDataModel("Magnus", b);
            b = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.zoidberg);
            PersonDataModel steffen = new PersonDataModel("Steffen", b);
            b = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.panda);
            PersonDataModel kolbein = new PersonDataModel("Kolbein", b);

            addPerson(magnus);
            addPerson(steffen);
            addPerson(kolbein);
        }
    }


}
