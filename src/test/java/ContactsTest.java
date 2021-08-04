import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class ContactsTest {
    private static Contacts contacts;
    private static Contact contact1;
    private static Contact contact2;
    private static Contact contact3;

    @BeforeAll
    static void SetUp() {
        contacts = new Contacts();
        contact1 = new Contact("Iakov", "Tenilin", "12345", Group.HOME);
        contact2 = new Contact("Ivan", "Ivanov", "569", Group.WORK);
        contact3 = new Contact("Anna", "Svetlakova", "8785", Group.FAMILY);
        contacts.contacts.put("12345", contact1);
        contacts.contacts.put("569", contact2);
        contacts.contacts.put("8785", contact3);
    }

    @Test
    void findContactsByNameSurname() {
        ArrayList<Contact> expected = new ArrayList<>();
        expected.add(contact1);

        ArrayList<Contact> result = contacts.findContactsByNameSurname("Iakov", "Tenilin");

        Assertions.assertEquals(result, expected);

    }

    @Test
    void findContactByNumber() {
        String expected = "Anna Svetlakova 8785 Group:Family";

        String result = contacts.findContactByNumber("8785");

        Assertions.assertEquals(expected, result);
    }
}