package hvl.nameapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by Steffen on 13.01.2018.
 */

public class StudentDataModel implements Serializable {
    private String name;
    private byte [] picture;

    public StudentDataModel(String name, Bitmap picture){
        this.name = name;
        this.picture = bytifyPicture(picture);
    }

    public StudentDataModel(String name, byte[] picture){
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPicture() {return BitmapFactory.decodeByteArray(picture, 0, picture.length);}

    public void setPicture(Bitmap picture) {
        this.picture = bytifyPicture(picture);
    }

    public void setPicture(byte[] picture){ this.picture = picture; }

    public byte[] bytifyPicture (Bitmap picture){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        picture.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
