import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Contacts contacts = new Contacts();
        MissedCalls missedCalls = new MissedCalls();
        while (true) {
            System.out.println("Menu:\n 1. Add contact\n 2. Add missed call\n 3. Display all missed calls\n 4. Display all contacts\n 5. Clear missed calls\n 6. Edit contact\n 7. Delete contact\n 8. Exit");
            System.out.println("Input menu number (1-7): ");
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 8) {
                    break;
                }
                switch (input) {
                    case 1:
                        addContact(contacts);
                        break;
                    case 2:
                        addMissedCall(missedCalls, contacts);
                        break;
                    case 3:
                        displayMissedCalls(missedCalls);
                        break;
                    case 4:
                        displayAllContacts(contacts);
                        break;
                    case 5:
                        clearMissedCalls(missedCalls);
                        break;
                    case 6:
                        editContact(contacts);
                        break;
                    case 7:
                        deleteContact(contacts);
                        break;
                }
            }catch (Exception e) {
                System.out.println("Input error");
            }

        }
    }

    private static void displayAllContacts(Contacts contacts) {
        System.out.println("Contact list:\n" + contacts);
    }

    private static void addContact(Contacts contacts) {
        while (true) {
            System.out.println("Add name surname phone and group " + Arrays.toString(Group.values()) + " (with space or end to exit):");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] contactDetail = input.split(" ");
            if (hasInputError(contactDetail, 4)) {
                continue;
            }
            if (contacts.addContact(contactDetail[0], contactDetail[1], contactDetail[2], contactDetail[3])) {
                System.out.println("Contact added successfully");
            } else {
                System.out.println("Contact already exist");
            }
        }
    }

    private static void addMissedCall(MissedCalls missedCalls, Contacts contacts) {
        while (true) {
            System.out.println("Add missed call (or end to exit): ");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            missedCalls.addMissedCall(contacts.findContactByNumber(input));
        }
    }

    private static void displayMissedCalls(MissedCalls missedCalls) {
        System.out.println("Missed calls list:\n " + missedCalls);
    }

    private static void clearMissedCalls(MissedCalls missedCalls) {
        if (missedCalls.clearMissedCalls()) {
            System.out.println("Missed calls clear successfully!");
        }
    }

    private static void deleteContact(Contacts contacts) {
        while (true) {
            System.out.println("Input name and surname for delete contact (with space or end to exit):");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] detailContact = input.split(" ");
            if (hasInputError(detailContact, 2)) {
                continue;
            }
            ArrayList<Contact> contactsList = contacts.findContactsByNameSurname(detailContact[0], detailContact[1]);
            if (contactsList.isEmpty()) {
                System.out.println("Contacts for deleting not found!");
                continue;
            }
            printFoundContacts(contactsList);
            System.out.println("Which contact do you want to delete? (number or 0 for exit):");
            try {
                int number = Integer.parseInt(scanner.nextLine());
                if (number == 0) {
                    System.out.println("Cancel deletion");
                    break;
                }
                if (number < 0 || number > contactsList.size()) {
                    System.out.println("Invalid number!");
                    continue;
                }
                if (contacts.delContactByPhone(contactsList.get(number-1).getPhone())){
                    System.out.println("Deleted successfully!");
                    break;
                } else {
                    System.out.println("Delete error!");
                }
            }catch (Exception e) {
                System.out.println("Input error!");
            }
        }
    }

    private static void editContact(Contacts contacts) {
        while (true){
            System.out.println("Input name and surname for find to edit contact (with space or end to exit):");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] detailContact = input.split(" ");
            if (hasInputError(detailContact, 2)) {
                continue;
            }
            ArrayList<Contact> contactsList = contacts.findContactsByNameSurname(detailContact[0], detailContact[1]);
            if (contactsList.isEmpty()) {
                System.out.println("Contacts for editing not found!");
                continue;
            }
            int number = 1;
            if (contactsList.size() != 1) {
                printFoundContacts(contactsList);
                System.out.println("Which contact do you want to edit? (number or 0 for exit):");
                number = Integer.parseInt(scanner.nextLine());
                if (number == 0) {
                    System.out.println("Cancel edition");
                    break;
                }
                if (number < 0 || number > contactsList.size()) {
                    System.out.println("Invalid number!");
                    continue;
                }
            }
            System.out.println("Editing contact: ");
            System.out.println(contactsList.get(number-1));
            System.out.println("Edit name? (new name or enter)");
            String newName = scanner.nextLine();
            System.out.println("Edit surname? (new surname or enter)");
            String newSurname = scanner.nextLine();
            System.out.println("Edit phone? (new phone or enter)");
            String newPhone = scanner.nextLine();
            String newGroup;
            while (true) {
                System.out.println("Edit group? (new group or enter)");
                newGroup = scanner.nextLine();
                if (newGroup.equals("") || Group.contains(newGroup)) {
                    break;
                }
                System.out.println("Group name error");
            }
            if (contacts.editContact(contactsList.get(number-1).getPhone(), newName, newSurname, newPhone, newGroup)){
                System.out.println("Contact edited successfully!");
                break;
            } else {
                System.out.println("Contact edited error!");
            };
        }
    }

    private static boolean hasInputError(String[] input, int wordsCount) {
        if (input.length != wordsCount) {
            System.out.println("Format input error!");
            return true;
        }
        return false;
    }

    private static void printFoundContacts(ArrayList<Contact> contacts){
        for (int i = 0; i < contacts.size(); i++){
            System.out.println((i+1) + ". " + contacts.get(i));
        }
    }
}