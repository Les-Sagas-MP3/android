package fr.lessagasmp3.android.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import fr.lessagasmp3.android.persistence.DateConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(tableName = "rss_message_table")
@TypeConverters(DateConverter.class)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class RssMessage extends Audit {

    @NonNull
    @ColumnInfo(name = "feed_title")
    private String feedTitle;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "pubdate")
    private String pubdate;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "link")
    private String link;

    @NonNull
    @ColumnInfo(name = "author")
    private String author;

    @NonNull
    @ColumnInfo(name = "guid")
    private String guid;

}
