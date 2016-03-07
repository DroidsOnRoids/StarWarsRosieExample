package pl.droidsonroids.rosieexample.model;

import com.google.gson.annotations.SerializedName;
import com.karumi.rosie.repository.datasource.Identifiable;

public class Person implements Identifiable<Long> {

    private long id;
    private String name;
    private Gender gender;
    private String birth_year;
    private String url;

    public Person(final String name, final Gender gender, final String birth_year, final String url) {
        try {
            this.id = Long.valueOf(url.replaceAll("http://swapi.co/api/people/", "").replaceAll("/", ""));
        } catch (NumberFormatException e) {
            //no-op
        }
        this.name = name;
        this.gender = gender;
        this.birth_year = birth_year;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getBirthYear() {
        return birth_year;
    }

    @Override
    public Long getKey() {
        return id;
    }

    public enum Gender {
        @SerializedName("male")
        MALE,
        @SerializedName("female")
        FEMALE,
        @SerializedName("hermaphrodite")
        HERMAPHRODITE,
        UNKNOWN
    }
}
