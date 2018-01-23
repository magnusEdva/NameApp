package hvl.nameapp;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by zorri on 23/01/2018.
 */

public class NameApp extends Application {
    private ArrayList<StudentDataModel> students;

    @Override
    public void onCreate() {
        super.onCreate();
        if (students == null)
            onInit();
    }

    public ArrayList<StudentDataModel> getStudents() {
        return students;
    }

    public void addStudent(StudentDataModel s) {
        students.add(s);
    }

    // Sets dummy data.
    private void onInit() {
        students = new ArrayList<StudentDataModel>();
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.elmo);
        StudentDataModel magnus = new StudentDataModel("Magnus", b);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.zoidberg);
        StudentDataModel steffen = new StudentDataModel("Steffen", b);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.panda);
        StudentDataModel kolbein = new StudentDataModel("Kolbein", b);
        students.add(magnus);
        students.add(steffen);
        students.add(kolbein);
    }
}


