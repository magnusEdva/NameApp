package hvl.nameapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by zorri on 25/01/2018.
 */
@Dao
public interface PersonDAO {
        @Query("SELECT * FROM persons")
        List<PersonDataModel> getAll();

        @Query("SELECT * FROM persons WHERE picture IN (:pictures)")
        List<PersonDataModel> loadAllByIds(String[] pictures);

        @Query("SELECT * FROM persons WHERE name LIKE :name LIMIT 1")
        PersonDataModel findByName(String name);

        @Insert
        void insertAll(PersonDataModel... persons);

        @Delete
        void delete(PersonDataModel person);


}
