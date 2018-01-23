package hvl.nameapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;

/**
 * Created by Steffen on 13.01.2018.
 */

public class PersonDataModel implements Serializable {
    private String name;
    private String picture;

    //path to files in system - kinda hacky set in NameApp.java onCreate
    public static String dir;

    public PersonDataModel(String name, Bitmap picture){
        this.name = name;
        //store picture in storage and return file name
        this.picture = pathNameValidity(picture, name);
    }

    /**
     * Used only for exisiting Persons fetched from file
     * @param name of Person
     * @param picture String with path to pre stored picture.
     */
    public PersonDataModel(String name, String picture){
        this.name = name;
        this.picture = picture;
    }
    public String getName() {
        return name;
    }

    public Bitmap getPicture() {return fetchPicture();}

    /**
     * Upon first initiating a Person stores their picture to disk
     * @param picture Bitmap to be stored
     * @param name candidate for this.picture, changed if already exists
     * @return
     */
    private String storePicture(Bitmap picture , String name){
        FileOutputStream out = null;
        try {
            Log.d("dir", dir + "/" + name);
            out = new FileOutputStream(dir + "/" + name);
            picture.compress(Bitmap.CompressFormat.PNG,100, out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return name ;
    }

    /**
     * fetches Bitmap picture from file with this.picture as path.
     * @return Bitmap picture
     */
    private Bitmap fetchPicture(){
        FileInputStream fileIn = null;
        Bitmap picture = null;
        try{
            fileIn = new FileInputStream(dir + "/" + this.picture.trim());
            picture = BitmapFactory.decodeStream(fileIn);
        } catch(Exception e){
            e.printStackTrace();
        } finally {

            try {
                    fileIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return picture;
    }

    /**
     *
     * @param picture Bitmap to be stored
     * @param name candidate for this.picture
     * @return value for this.picture
     */
    private String pathNameValidity(Bitmap picture, String name){
        File file = new File(dir + "/" + name);
        if(file.exists()){
            name = name + 1;
            //change name slightly to make a new uniquely named file
            return pathNameValidity(picture, name);
        }
        else
            return storePicture(picture, name);
    }

    @Override
    public String toString(){
        return name + "|" + picture;
    }
}
