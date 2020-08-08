package fr.lessagasmp3.android.model;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class Saga extends Audit<String> {

    private String title = "";

    private String url = "";

    private String urlWiki = "";

    private Set<Author> authors = new LinkedHashSet<>();

    private Set<Category> categories = new LinkedHashSet<>();

    private Integer levelArt = 100;

    private Integer levelTech = 100;

    private Integer nbReviews = 0;

    private String urlReviews = "";

    private Integer nbBravos = 0;

}
