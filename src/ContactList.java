/*
 * Name: Janet Lin
 * PID:  A16686792
 */

import java.util.*;

public class ContactList {
	
	// Add instance variables here
    private BSTree<String> tree;
    private HashMap<String, Person> map;
    private int nContacts;

    public ContactList() {
        this.tree = new BSTree<>();
        this.map = new HashMap<String, Person>();
        this.nContacts = 0;
    }
	
    public boolean createContact(Person person) {
        if (lookupContact(person.getName()))
            return false;
        else {
            this.map.put(person.getName(), person);
            this.nContacts++;
            return true;
        }
    }

    public boolean lookupContact(String name) {
        return this.map.containsKey(name);
    }

    public Person getContact(String name) {

        if (this.map.containsKey(name)) {
            return this.map.get(name);
        }
        return null;
    }

    public Person[] getContactByRange(String start, String end) {

        for (String i : this.map.keySet()) {

            if (i.substring(0,start.length()).compareTo(start) >= 0) {
                System.out.println(1);
            }
        }

        return null;
    }

    public boolean deleteContact(String name) {
        if (!this.map.containsKey(name)) {
            return false;
        }

        this.map.remove(name);
        this.nContacts--;
        return true;
    }

    public int size() {
        return this.nContacts;
    }

    public String[] fetchAllNames() {
        String[] ans = new String[this.size()];
        int count = 0;
        TreeMap<String, Person> sorted = new TreeMap<>(this.map);
        for (Map.Entry<String, Person> entry : sorted.entrySet())
            ans[count++] = entry.getKey();
        return ans;
    }

    public String[] fetchAllPhoneNumbers() {

        ArrayList<ArrayList<String>> temp = new ArrayList<>();
        for (Person i: this.map.values()) {
            temp.add(i.getPhoneNumbers());
        }
        String ans[]=temp.toArray(new String[temp.size()]);
        return ans;
    }
}
