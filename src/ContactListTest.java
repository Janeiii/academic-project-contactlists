import static org.junit.Assert.*;
import java.util.*;

public class ContactListTest {

    private ArrayList<String> phone1;
    private ContactList book;
    private Person a;

    @org.junit.Before
    public void setUp() throws Exception {
        phone1 = new ArrayList<>();
        phone1.add("916");
        phone1.add("542");
        phone1.add("698");
        a = new Person("A",phone1);
        book = new ContactList();
    }

    @org.junit.Test
    public void createContact() {
        book.createContact(a);
        System.out.println(book.lookupContact("B"));

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