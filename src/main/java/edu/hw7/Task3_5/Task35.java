package edu.hw7.Task3_5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task35 implements PersonDatabase {

    private final Map<Integer, Person> personsById = new HashMap<>();
    private final Map<String, List<Person>> personsByName = new HashMap<>();
    private final Map<String, List<Person>> personsByAddress = new HashMap<>();
    private final Map<String, List<Person>> personsByPhone = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            validatePerson(person);
            personsById.put(person.id(), person);
            personsByName.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
            personsByAddress.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
            personsByPhone.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = personsById.remove(id);
            if (person != null) {
                personsByName.get(person.name()).remove(person);
                personsByAddress.get(person.address()).remove(person);
                personsByPhone.get(person.phoneNumber()).remove(person);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return new ArrayList<>(personsByName.getOrDefault(name, new ArrayList<>()));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return new ArrayList<>(personsByAddress.getOrDefault(address, new ArrayList<>()));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return new ArrayList<>(personsByPhone.getOrDefault(phone, new ArrayList<>()));
        } finally {
            lock.readLock().unlock();
        }
    }

    private void validatePerson(Person person) {
        if (person.name() == null || person.address() == null || person.phoneNumber() == null) {
            throw new IllegalArgumentException("Имя, адрес и номер телефона человека не могут быть null.");
        }
        if (personsById.containsKey(person.id())) {
            throw new IllegalArgumentException("Человек с ID " + person.id() + " уже существует.");
        }
    }
}
