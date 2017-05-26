import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Moses Franco and Roxana Pupo on 5/25/17
 * Codeup
 * Pinnacles
 */
public class ContactsManager {
	public static void main(String[] args) throws IOException {
		String directory = "info";
		String filename = "contacts.txt";
		List<String> contactName = new ArrayList<>(), contactNumber = new ArrayList<>();

		Path dataDirectory = Paths.get(directory);
		Path dataFile = Paths.get(directory, filename);

		if (Files.notExists(dataDirectory)) {
			Files.createDirectories(dataDirectory);
		}

		if (Files.notExists(dataFile)) {
			Files.createFile(dataFile);
		}

		Scanner scanner = new Scanner(System.in);

		int menuOption = menuOptions(scanner);
		String searchingContact;

		readFile(dataFile, contactName, contactNumber);

		switch (menuOption) {
			case 1:
				for (int i = 0; i < contactName.size(); i++) {
					System.out.println(contactName.get(i) + contactNumber.get(i));
				}
				break;
			case 2:
				newContact(contactName, contactNumber, scanner);

				writeFile(dataFile, contactName, contactNumber);

//				Creates local variable that concatenates contact info to be passed into the file
//				String infoToAdd = contactName + " | " + contactNumber;
//				Files.write(dataFile, Arrays.asList(infoToAdd), StandardOpenOption.APPEND);
				break;
			case 3:
				System.out.println("Enter contact name: ");
				searchingContact = scanner.nextLine();
				scanner.nextLine();
				for (int i = 0; i < contactName.size(); i++) {
					if (contactName.get(i).equalsIgnoreCase(searchingContact)) {
						printPhoneNumber(contactNumber, i);
						break;
					} else {
						System.out.println("We don't know that person");
					}
				}
				break;
			case 4:
				System.out.println("Enter contact name: ");
				searchingContact = scanner.nextLine();
				scanner.nextLine();
				for (int i = 0; i < contactName.size(); i++) {
					if (contactName.get(i).equalsIgnoreCase(searchingContact)) {
						deleteContact(contactName, contactNumber, i);
						break;
					} else {
						System.out.println("We don't know that person");
					}
				}
				break;
			default:
				readFile(dataFile, contactName, contactNumber);
			case 5:
				System.out.println("Good bye!");
				writeFile(dataFile, contactName, contactNumber);
		}
	}

	private static void newContact(List<String> contactName, List<String> contactNumber, Scanner scanner) {
		System.out.println("Enter your contacts name:");
		contactName.add(scanner.nextLine());
		System.out.println("Enter contact phone number");
		contactNumber.add(scanner.nextLine());
	}

	private static void printPhoneNumber(List<String> contactNumber, int i) {
		System.out.println("Contact phone number: " + contactNumber.get(i));
	}

	private static void deleteContact(List<String> contactName, List<String> contactNumber, int i) {
		contactName.remove(i);
		contactNumber.remove(i);
	}

	private static int menuOptions(Scanner scanner) {
		System.out.println("" +
				"1. View contacts.\n" +
				"2. Add a new contact.\n" +
				"3. Search a contact by name.\n" +
				"4. Delete an existing contact.\n" +
				"5. Exit.\n" +
				"Enter an option (1, 2, 3, 4 or 5):");


		int menuOption = scanner.nextInt();
		scanner.nextLine();
		return menuOption;
	}

	public static void readFile(Path dataFile, List<String> contactName, List<String> contactNumber) throws IOException {
		List<String> contacts = Files.readAllLines(dataFile);
		for (String contact : contacts) {
			contactName.add(contact.substring(0, contact.indexOf("|") - 1));
			contactNumber.add(contact.substring(contact.indexOf("|") + 2, contact.length()));
		}
//		for (String contact : contacts) {
//			 contactName.add(contact.split(" | ")[0]);
//			contactNumber.add(contact.split(" | ")[3]);
//		}
//		System.out.println(contacts.size());
	}

	public static void writeFile(Path dataFile, List<String> contactName, List<String> contactNumber) throws IOException {
		List<String> infoToAdd = new ArrayList<>();
		for (int i = 0; i < contactName.size(); i++) {
			infoToAdd.add(contactName.get(i) + " | " + contactNumber.get(i));
			System.out.println(infoToAdd.get(i));
		}
		Files.write(dataFile, infoToAdd);
	}
}
