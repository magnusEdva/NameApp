package hvl.nameapp;

import java.io.Serializable;

/**
 * Created by Steffen on 13.01.2018.
 */

public class StudentDataModel implements Serializable {
    String name;
    String picture;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
