package main;

import interfaces.AdressBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master on 24.04.2017.
 */
public class Base implements AdressBook{
    private ObservableList<Person> list =  FXCollections.observableArrayList();

    public ObservableList<Person> getList() {
        return list;
    }

    public void initBase(){
        list.add(new Person("Bob", "111-111-111"));
        list.add(new Person("Jack", "222-222-222"));
        list.add(new Person("Nick", "333-333-333"));
        list.add(new Person("Julia", "444-444-444"));
        list.add(new Person("Ingrid", "555-555-555"));
        list.add(new Person("Zara", "666-666-666"));
        list.add(new Person("Luis", "777-777-777"));
    }

    @Override
    public void add(Person person) {
        list.add(person);
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void remove(Person person) {
        list.remove(person);
    }
}
