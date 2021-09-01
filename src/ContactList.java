/*
 * Name: Janet Lin
 * PID:  A16686792
 */

import java.util.*;

public class ContactList {
	
	// Add instance variables here
    private BSTree<Person> tree;
    private BSTree<String> nameTree;

    public ContactList() {
        this.tree = new BSTree<Person>();
        this.nameTree = new BSTree<String>();
    }

    public boolean createContact(Person person) {
        if (lookupContact(person.getName())) {
            return false;
        }
        this.tree.insert(person);
        this.nameTree.insert(person.getName());
        return true;
    }

    public boolean lookupContact(String name) {
        return this.nameTree.contains(name);
    }

    public Person getContact(String name) {
        if (!this.nameTree.contains(name))
            return null;
        else {

        }
    }

    public Person[] getContactByRange(String start, String end) {
        return null;
    }

    public boolean deleteContact(String name) {
        return true;
    }

    public int size() {
        return 42;
    }

    public String[] fetchAllNames() {
        return null;
    }

    public String[] fetchAllPhoneNumbers() {
        return null;
    }
}
