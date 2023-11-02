package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Task5 {

    private Task5() {
    }

    public static List<Contact> parseContacts(String[] names, String order) {
        if (names == null || names.length == 0) {
            return Collections.emptyList();
        }

        List<Contact> contacts = new ArrayList<>();
        for (String name : names) {
            contacts.add(new Contact(name));
        }

        contacts.sort((c1, c2) -> "ASC".equals(order) ? c1.compareTo(c2) : c2.compareTo(c1));
        return contacts;
    }

    public static class Contact implements Comparable<Contact> {
        private final String firstName;
        private final String lastName;

        public Contact(String fullName) {
            String[] parts = fullName.split(" ", 2);
            this.firstName = parts[0];
            this.lastName = (parts.length > 1) ? parts[1] : parts[0];
        }

        @Override
        public int compareTo(Contact other) {
            return this.lastName.compareTo(other.lastName);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Contact contact = (Contact) obj;
            return Objects.equals(firstName, contact.firstName)
                && Objects.equals(lastName, contact.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName);
        }

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }
}
