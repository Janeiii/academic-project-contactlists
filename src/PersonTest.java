import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersonTest {
    private Person asd;
    @org.junit.Before
    public void setUp() throws Exception {


        ArrayList<String> a = new ArrayList<String>();
        a.add("916");
        a.add("216");
        asd = new Person("asd",a);
    }

    @org.junit.Test
    public void getName() {
    }

    @org.junit.Test
    public void addPhoneNumber() {
    }

    @org.junit.Test
    public void getPhoneNumbers() {
    }

    @org.junit.Test
    public void deletePhoneNumber() {
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest() {
        asd.deletePhoneNumber(null);

    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentExceptionTest2() {
        asd.deletePhoneNumber("916");

    }
}