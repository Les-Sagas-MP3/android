package fr.lessagasmp3.android.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fr.lessagasmp3.android.entity.RssMessage;
import fr.lessagasmp3.android.entity.Saga;
import fr.lessagasmp3.android.persistence.dao.RssMessageDao;
import fr.lessagasmp3.android.persistence.dao.SagaDao;

@Database(entities = {RssMessage.class, Saga.class}, version = 2, exportSchema = false)
public abstract class MainRoomDatabase extends RoomDatabase {

    public abstract SagaDao sagaDao();
    public abstract RssMessageDao rssMessageDao();

    private static MainRoomDatabase INSTANCE;

    public static MainRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MainRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MainRoomDatabase.class, "lessagasmp3.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
