package pl.traineeship.autoqa.lesson8.phonebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Phonebook {
    private Map<String, Set<String>> phonebookMap = new HashMap<>();

    public void add(String surname, String phoneNumber) {
        if (phonebookMap.containsKey(surname)) {
            phonebookMap.get(surname).add(phoneNumber);
        } else {
            phonebookMap.put(surname, new HashSet<>(Set.of(phoneNumber)));
        }
    }

    public Set<String> get(String surname) {
        return phonebookMap.get(surname);
    }
}
