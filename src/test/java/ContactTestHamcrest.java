import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

class ContactTestHamcrest {

    @Test
    void testToString() {
        Contact contact = new Contact("Iakov", "Tenilin", "12345", Group.HOME);
        assertThat(contact, hasToString("Iakov Tenilin 12345 Group:Home"));
    }
}