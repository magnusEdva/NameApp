package hvl.nameapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
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
@Entity(tableName = "persons")
public class PersonDataModel implements Serializable {
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "picture")
    @NonNull
    @PrimaryKey
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

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Bitmap getPictureAsBitmap() {return fetchPicture();}

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
            picture.compress(Bitmap.CompressFormat.JPEG,20, out);
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

    public boolean deletePicture() {
        File picture = new File(dir + "/" + this.picture);
        boolean deleted = false;
        if (!picture.exists()){
            Log.d("Picture", "No picture upon deletting " + name);
        }
        else
            deleted = picture.delete();
        return deleted;
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
