package hvl.nameapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;

/**
 * Created by Steffen on 13.01.2018.
 */

public class StudentDataModel implements Serializable {
    private String name;
    private String picture;

    //path to files in system
    public static File dir;
    public StudentDataModel(String name, Bitmap picture){
        this.name = name;
        this.picture = storePicture(picture, name);
    }

    public StudentDataModel(String name, String picture){
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPicture() {return fetchPicture();}

    public void setPicture(Bitmap picture) {
        this.picture = storePicture(picture, this.name);
    }

    public void setPicture(String picture){ this.picture = picture; }

    private String storePicture(Bitmap picture , String name){
        FileOutputStream out = null;
        URI link = null;
        try {
            Log.d("dir", dir.getPath());
            out = new FileOutputStream(dir.getPath() + name);
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

    private Bitmap fetchPicture(){
        FileInputStream fileIn = null;
        Bitmap picture = null;
        try{
            fileIn = new FileInputStream(dir.getPath() + this.picture);
            picture = BitmapFactory.decodeStream(fileIn);
        } catch(Exception e){
            e.printStackTrace();
        }
        return picture;
    }
}
