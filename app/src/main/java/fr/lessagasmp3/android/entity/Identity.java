package fr.lessagasmp3.android.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
class Identity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    protected Long id;

}

