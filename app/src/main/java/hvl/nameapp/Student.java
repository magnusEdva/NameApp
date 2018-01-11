package hvl.nameapp;

/**
 * Created by zorri on 11/01/2018.
 */

public class Student {

    private String name;
    private String picture;
    public Student(String name, String picture){
        this.name = name;
        this.picture = picture;
    }
    public Student(String nameAndPicture){
        String [] student = nameAndPicture.split("\\|");
        name = student[0];
        picture = student[1];
    }

    public String getName(){
        return name;
    }

    public String getPicture(){
        return picture;
    }

    public void setName(String n){
        name = n;
    }
    public void setPicture(String p){
        picture = p;
    }

    @Override
    public String toString(){
        return name + "|" + picture;
    }
}
