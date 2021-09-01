import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ContactListTest {

    private ContactList lst;
    private Person asd;
    private Person zxc;

    @Before
    public void setUp() throws Exception {
        ArrayList<String> a = new ArrayList<String>();
        a.add("916");
        a.add("216");

        ArrayList<String> b = new ArrayList<String>();
        b.add("916");
        b.add("287");
        b.add("965");

        lst = new ContactList();

        asd = new Person("asd",a);
        zxc = new Person("zcx",b);
    }

    @Test
    public void createContact() {

        assertTrue(lst.createContact(asd));
        assertFalse(lst.createContact(asd));
        assertTrue(lst.createContact(zxc));



    }

    @Test
    public void lookupContact() {
    }

    @Test
    public void getContact() {
    }

    @Test
    public void getContactByRange() {
    }

    @Test
    public void deleteContact() {
    }

    @Test
    public void size() {
    }

    @Test
    public void fetchAllNames() {
    }

    @Test
    public void fetchAllPhoneNumbers() {
    }
}