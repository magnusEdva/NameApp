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
import java.lang.ref.WeakReference;
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
        new loadAll(this).execute();
    }


    //adds a person to the Java object
    public void addPerson(PersonDataModel p) {
        persons.add(p);
        new insertAll(this).execute(p);
    }

    public List<PersonDataModel> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDataModel> persons) {
        this.persons = persons;
    }

    public PersonDatabase getDatabase() {
        return database;
    }


    // Sets dummy data.
    private List<PersonDataModel> onInit() {
        //don't run if not empty
        ArrayList<PersonDataModel> temp = new ArrayList<>();
        NameApp na = (NameApp) mContext.getApplicationContext();

        Bitmap b = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.elmo);
        PersonDataModel magnus = new PersonDataModel("Magnus", b);
        b = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.zoidberg);
        PersonDataModel steffen = new PersonDataModel("Steffen", b);
        b = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.panda);
        PersonDataModel kolbein = new PersonDataModel("Kolbein", b);

        temp.add(magnus);
        temp.add(steffen);
        temp.add(kolbein);

        new insertAll(this).execute(magnus,steffen,kolbein);

        return temp;
    }

    private static class loadAll extends AsyncTask<Void, Void, List<PersonDataModel>> {

        private WeakReference<PersonManager> managerReference;

        // only retain a weak reference to the activity
        loadAll(PersonManager pm) {
            managerReference = new WeakReference<>(pm);
        }

        @Override
        protected List<PersonDataModel> doInBackground(Void... params) {
            return managerReference.get().getDatabase().personDAO().getAll();
        }

        @Override
        protected void onPostExecute(List<PersonDataModel> list) {
            if (list == null || list.isEmpty()) {
                list = managerReference.get().onInit();
            }
            managerReference.get().setPersons(list);
        }
    }

    static class insertAll extends AsyncTask<PersonDataModel, Void, Void> {

        private WeakReference<PersonManager> managerReference;

        // only retain a weak reference to the activity
        insertAll(PersonManager pm) {
            managerReference = new WeakReference<>(pm);
        }

        @Override
        protected Void doInBackground(PersonDataModel... persons) {
            managerReference.get().getDatabase().personDAO().insertAll(persons);
            return null;
        }
    }
}
