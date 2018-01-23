package hvl.nameapp;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
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
            students = new ArrayList<StudentDataModel>();
    }

    public ArrayList<StudentDataModel> getStudents() {
        return students;
    }

    public void addStudent(StudentDataModel s) {
        students.add(s);
    }


}


