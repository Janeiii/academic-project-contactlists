import static org.junit.Assert.*;
import java.util.*;

public class ContactListTest {

    private ArrayList<String> phone1;
    private ArrayList<String> phone2;
    private ContactList book;
    private Person a;
    private Person b;

    @org.junit.Before
    public void setUp() throws Exception {
        phone1 = new ArrayList<>();
        phone1.add("916");
        phone1.add("542");
        phone1.add("698");
        a = new Person("A",phone1);
        book = new ContactList();

        phone2 = new ArrayList<>();
        phone2.add("123");
        phone2.add("643");
        phone2.add("346");
        phone2.add("987");
        b = new Person("B",phone2);

    }

    @org.junit.Test
    public void createContact() {
        book.createContact(a);
        book.createContact(b);
        System.out.println(book.lookupContact("A"));
        System.out.println(book.lookupContact("B"));
        System.out.println(book.lookupContact("IK"));
        book.getContact("A").getPhoneNumbers();
        book.getContact("B").getPhoneNumbers();
        String[] m = book.fetchAllNames();
        for (String i: m)
            System.out.println(i);
        String[] p = book.fetchAllPhoneNumbers();
        for (String i: p)
            System.out.println(i);


    }

    @org.junit.Test
    public void lookupContact() {
    }

    @org.junit.Test
    public void getContact() {
    }

    @org.junit.Test
    public void getContactByRange() {
    }

    @org.junit.Test
    public void deleteContact() {
    }

    @org.junit.Test
    public void size() {
    }

    @org.junit.Test
    public void fetchAllNames() {
    }

    @org.junit.Test
    public void fetchAllPhoneNumbers() {
    }
}