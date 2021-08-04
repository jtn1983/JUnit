import java.util.*;

public class Contacts {
    Map<String, Contact> contacts = new HashMap<>();

    public boolean addContact(String name, String surname, String phone, String group){
        Contact contact = new Contact(name, surname, phone, Group.valueOf(group.toUpperCase()));
        if (contacts.containsKey(phone)) {
            return false;
        }
        contacts.put(phone, contact);
        return true;
    }

    public boolean delContactByPhone(String phone) {
        if (contacts.containsKey(phone)) {
            contacts.remove(phone);
            return true;
        }
        return false;
    }

    public boolean editContact (String index, String newName, String newSurname, String newPhone, String newGroup) {
        if (!contacts.containsKey(index)) {
            return false;
        }
        Contact contact = contacts.get(index);
        if (!newPhone.equals("")) {
            contacts.remove(index);
            contacts.put(newPhone, new Contact(
                    newName.equals("") ? contact.getName(): newName,
                    newSurname.equals("") ? contact.getSurname() : newSurname,
                    newPhone,
                    newGroup.equals("") ? contact.getGroup() : Group.valueOf(newGroup.toUpperCase())
            ));
        }else {
            if (!newName.equals("")) {
                contact.setName(newName);
            }
            if (!newSurname.equals("")) {
                contact.setSurname(newSurname);
            }
            if (!newGroup.equals("")) {
                contact.setGroup(Group.valueOf(newGroup.toUpperCase()));
            }
        }
        return true;
    }

    public ArrayList<Contact> findContactsByNameSurname(String name, String surname) {
        ArrayList<Contact> listContacts = new ArrayList<>();
        for(Map.Entry<String, Contact> contactEntry : contacts.entrySet()) {
            if (contactEntry.getValue().getName().equals(name) && contactEntry.getValue().getSurname().equals(surname)) {
                listContacts.add(contactEntry.getValue());
            }
        }
        return listContacts;
    }

    public String findContactByNumber(String phone){
        if (contacts.containsKey(phone)) {
            Contact contact = contacts.get(phone);
            return contact.toString();
        }
        return phone;
    }

    @Override
    public String toString() {
        if (contacts.isEmpty()) {
            return "Contact list is empty";
        }
        StringBuilder contactBook = new StringBuilder();
        for (Map.Entry<String, Contact> contactEntry : contacts.entrySet()){
            contactBook.append(contactEntry.getValue().toString());
            contactBook.append("\n");
        }
        return contactBook.toString();
    }
}