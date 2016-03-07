package pl.droidsonroids.rosieexample.model;

import java.util.List;

public class People {

    private int count;
    private String next;
    private List<Person> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Person> getPersonList() {
        return results;
    }
}
