package fr.lessagasmp3.android.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.TypeConverters;

import java.util.LinkedHashSet;
import java.util.Set;

import fr.lessagasmp3.android.common.StringCommon;
import fr.lessagasmp3.android.persistence.DateConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(tableName = "saga_table")
@TypeConverters(DateConverter.class)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class Saga extends Audit {

    @NonNull
    @ColumnInfo(name = "title")
    private String title = StringCommon.EMPTY;

    @NonNull
    @ColumnInfo(name = "url")
    private String url = StringCommon.EMPTY;

    @NonNull
    @ColumnInfo(name = "url_wiki")
    private String urlWiki = StringCommon.EMPTY;

    @NonNull
    @ColumnInfo(name = "level_art")
    private Integer levelArt = 100;

    @NonNull
    @ColumnInfo(name = "level_tech")
    private Integer levelTech = 100;

    @NonNull
    @ColumnInfo(name = "nb_reviews")
    private Integer nbReviews = 0;

    @NonNull
    @ColumnInfo(name = "url_reviews")
    private String urlReviews = StringCommon.EMPTY;

    @NonNull
    @ColumnInfo(name = "nb_bravos")
    private Integer nbBravos = 0;

    @Ignore
    private Set<Author> authors = new LinkedHashSet<>();

    @Ignore
    private Set<Category> categories = new LinkedHashSet<>();

}
