package fr.lessagasmp3.android.persistence.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fr.lessagasmp3.android.entity.Saga;
import fr.lessagasmp3.android.persistence.dao.SagaDao;

@Database(entities = {Saga.class}, version = 1, exportSchema = false)
public abstract class SagaRoomDatabase extends RoomDatabase {

    public abstract SagaDao dao();

    private static SagaRoomDatabase INSTANCE;

    public static SagaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SagaRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SagaRoomDatabase.class, "saga_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
