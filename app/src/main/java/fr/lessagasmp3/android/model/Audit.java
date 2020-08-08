package fr.lessagasmp3.android.model;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
class Audit<U> extends Identity {

    private Date createdAt;

    private U createdBy;

    private Date updatedAt;

    private U updatedBy;

}

