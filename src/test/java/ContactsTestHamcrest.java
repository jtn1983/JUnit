import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

public class ContactsTestHamcrest {
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
    void addContact() {
        contacts.addContact("Svetlana", "Petrova", "890", "Home");
        assertThat(contacts.contacts, hasEntry("890", new Contact("Svetlana", "Petrova", "890", Group.HOME)));
    }

    @Test
    void delContactByPhone() {
        contacts.delContactByPhone("12345");
        assertThat(contacts.contacts, not(hasEntry("12345", contact1)));
    }

    @Test
    void findContactsByNameSurname() {
        ArrayList<Contact> expected = new ArrayList<>();
        expected.add(contact1);
        assertThat(contacts.findContactsByNameSurname("Iakov", "Tenilin"), equalTo(expected));
    }

    @Test
    void findContactByNumber() {
        assertThat(contacts.findContactByNumber("8785"), equalTo("Anna Svetlakova 8785 Group:Family"));
    }
}
