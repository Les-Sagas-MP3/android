package fr.lessagasmp3.android.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class RssMessage extends Audit<String>  {

    private String feedTitle;

    private String title;

    private String pubdate;

    private String description;

    private String link;

    private String author;

    private String guid;

}
