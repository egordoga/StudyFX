package main;

import interfaces.AdressBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAddressBook implements AdressBook{
    private ObservableList<Person> personList =  FXCollections.observableArrayList();

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public void initBase(){
        personList.add(new Person("Bob", "111-111-111"));
        personList.add(new Person("Jack", "222-222-222"));
        personList.add(new Person("Nick", "333-333-333"));
        personList.add(new Person("Julia", "444-444-444"));
        personList.add(new Person("Ingrid", "555-555-555"));
        personList.add(new Person("Zara", "666-666-666"));
        personList.add(new Person("Luis", "777-777-777"));
    }

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void remove(Person person) {
        personList.remove(person);
    }
}
