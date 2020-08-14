package fr.lessagasmp3.android.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import java.util.Date;

import fr.lessagasmp3.android.common.StringCommon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
class Audit extends fr.lessagasmp3.android.entity.Identity {

    @NonNull
    @ColumnInfo(name = "created_at")
    protected Date createdAt = new Date();

    @NonNull
    @ColumnInfo(name = "created_by")
    protected String createdBy = StringCommon.EMPTY;

    @NonNull
    @ColumnInfo(name = "updated_at")
    protected Date updatedAt = new Date();

    @NonNull
    @ColumnInfo(name = "updated_by")
    protected String updatedBy = StringCommon.EMPTY;

}

