package hvl.nameapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by zorri on 25/01/2018.
 */
@Database(entities = {PersonDataModel.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {
    public abstract PersonDAO personDAO();
}
