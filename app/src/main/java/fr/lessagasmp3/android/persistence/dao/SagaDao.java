package fr.lessagasmp3.android.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.lessagasmp3.android.entity.Saga;

@Dao
public interface SagaDao {

    @Insert
    void insert(Saga saga);

    @Query("DELETE FROM saga_table")
    void deleteAll();

    @Query("SELECT * from saga_table ORDER BY title ASC")
    LiveData<List<Saga>> getAllSagas();

}
