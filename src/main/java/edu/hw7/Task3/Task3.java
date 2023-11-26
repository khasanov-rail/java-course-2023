package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 implements PersonDatabase {

    private final Map<Integer, Person> personsById = new HashMap<>();
    private final Map<String, List<Person>> personsByName = new HashMap<>();
    private final Map<String, List<Person>> personsByAddress = new HashMap<>();
    private final Map<String, List<Person>> personsByPhone = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        validatePerson(person);
        personsById.put(person.id(), person);
        personsByName.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
        personsByAddress.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
        personsByPhone.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = personsById.remove(id);
        if (person != null) {
            personsByName.get(person.name()).remove(person);
            personsByAddress.get(person.address()).remove(person);
            personsByPhone.get(person.phoneNumber()).remove(person);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return new ArrayList<>(personsByName.getOrDefault(name, new ArrayList<>()));
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return new ArrayList<>(personsByAddress.getOrDefault(address, new ArrayList<>()));
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return new ArrayList<>(personsByPhone.getOrDefault(phone, new ArrayList<>()));
    }

    private void validatePerson(Person person) {
        if (person.name() == null || person.address() == null || person.phoneNumber() == null) {
            throw new IllegalArgumentException("Имя, адрес и номер телефона человека не могут быть null!");
        }
        if (personsById.containsKey(person.id())) {
            throw new IllegalArgumentException("Человек с ID " + person.id() + " уже существует!");
        }
    }
}
