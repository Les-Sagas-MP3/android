package fr.lessagasmp3.android.model;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString(exclude = {"sagas"})
public class Category extends Audit<String> {

    private Long id;

    private String name = "";

    private Integer nbSagas = 0;

    private Set<Saga> sagas = new LinkedHashSet<>();

}
