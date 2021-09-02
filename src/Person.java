/*
 * Name: Janet Lin
 * PID:  A16686792
 */

import java.util.*;

public class Person{

    // Add instance variables here
    private String name;
    private BSTree<String> tree;
    private ArrayList<String> pnArray;

    public Person(String name, ArrayList<String> pnArray) {

        this.name = name;
        this.tree = new BSTree<String>();
        this.pnArray = pnArray;

        for (String i : pnArray) {
            this.tree.insert(i);
        }
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getpnArray() {
        return this.pnArray;
    }

    public boolean addPhoneNumber(String pn) {
        if (this.tree.contains(pn))
            return false;
        this.tree.insert(pn);
        return true;
    }

    public ArrayList<String> getPhoneNumbers() {

        ArrayList<String> temp = this.tree.printInOrder();
        for (String i :temp)
            System.out.println(i);
        System.out.println(temp.size());

        return temp;
    }

    public boolean deletePhoneNumber(String pn) {

        if (pn == null || this.tree.getSize() <= 1)
            throw new IllegalArgumentException();

        if (this.tree.contains(pn) && this.tree.remove(pn))
            return true;

        else
            return false;

    }

}