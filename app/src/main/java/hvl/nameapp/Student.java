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

    @Override
    public String toString(){
        return name + "|" + picture;
    }
}
