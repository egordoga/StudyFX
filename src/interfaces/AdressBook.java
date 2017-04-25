package interfaces;

import main.Person;

/**
 * Created by Master on 24.04.2017.
 */
public interface AdressBook {
    void add(Person person);
    void update(Person person);
    void remove(Person person);
}
