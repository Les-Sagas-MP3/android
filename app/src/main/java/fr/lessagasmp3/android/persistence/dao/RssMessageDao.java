package fr.lessagasmp3.android.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.lessagasmp3.android.entity.RssMessage;

@Dao
public interface RssMessageDao {

    @Insert
    void insert(RssMessage rssMessage);

    @Query("DELETE FROM rss_message_table")
    void deleteAll();

    @Query("SELECT * from rss_message_table ORDER BY id DESC")
    LiveData<List<RssMessage>> getAllRssMessages();

}
