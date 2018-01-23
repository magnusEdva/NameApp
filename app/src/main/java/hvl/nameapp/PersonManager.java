package hvl.nameapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

/**
 * Created by zorri on 23/01/2018.
 */

public class PersonManager {
    private ArrayList<PersonDataModel> persons;
    private static String FILE_PATH;
    private Context mContext;

    public PersonManager(Context mContext) {
        this.mContext = mContext;
        this.FILE_PATH = mContext.getFilesDir() + "/" + mContext.getResources().getString(R.string.PersonRegister);
        persons = new ArrayList<>();
        getPersonsFromFile(FILE_PATH);
        onInit();
    }

    /**
     * gets all stored users from a predefined file
     * @param filePath
     */
    private void getPersonsFromFile(String filePath) {
        File fl = new File(filePath);
        if (!checkFile(fl))
            Log.d("File", "Failed to make file");
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(fl);
            //call here to get the method which generates models
            convertStreamToModel(fin);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null)
                    //close stream
                    fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //called by getPersonsFromFile - reads the file line by line and adds each line to the list of persons
    private void convertStreamToModel(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;

        while ((line = reader.readLine()) != null) {
            persons.add(reCreatePerson(line));
        }
        reader.close();

    }


    //takes a line from the file and adds it via addPerson
    private PersonDataModel reCreatePerson(String line) {
        PersonDataModel p = null;

        String[] values = line.split("\\|");

        //array values are always stored like this.
        p = new PersonDataModel(values[0], values[1]);
        return p;
    }

    /**
     * checks if file exists and creates it if it does not
     *
     * @return status of file.
     */
    private boolean checkFile(File fl) {
        boolean resultOk = fl.exists();
        if (!resultOk) {
            try {
                resultOk = fl.createNewFile();
                Log.d("missing File", "Made new");
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        return resultOk;
    }

    //adds one persons to the admin file
    private void writeToFile(PersonDataModel p) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(mContext.openFileOutput(mContext.getResources().getString(R.string.PersonRegister), mContext.MODE_APPEND));
            outputStreamWriter.write(p.toString() + "\n");
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //adds a person to the Java object
    public void addPerson(PersonDataModel p) {
        writeToFile(p);
        persons.add(p);
    }

    public ArrayList<PersonDataModel> getPersons() {
        return persons;
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
