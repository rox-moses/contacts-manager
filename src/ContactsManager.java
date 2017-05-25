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

		System.out.println("" +
				"1. View contacts.\n" +
				"2. Add a new contact.\n" +
				"3. Search a contact by name.\n" +
				"4. Delete an existing contact.\n" +
				"5. Exit.\n" +
				"Enter an option (1, 2, 3, 4 or 5):");


		int menuOption = scanner.nextInt();
		scanner.nextLine();
		String searchingContact = scanner.nextLine();

		readFile(dataFile, contactName, contactNumber);

		switch (menuOption) {
			case 1:
				List<String> contacts = Files.readAllLines(dataFile);
				for (String contact : contacts) {
					System.out.println(contact);
				}
				break;
			case 2:
				System.out.println("Enter your contacts name:");
				contactName.add(scanner.nextLine());
				System.out.println("Enter contact phone number");
				contactNumber.add(scanner.nextLine());

//				System.out.println(contactName.get(0) + contactNumber.get(0));
				writeFile(dataFile, contactName, contactNumber);

//				Creates local variable that contactenates contact info to be passed into the file
//				String infoToAdd = contactName + " | " + contactNumber;
//				Files.write(dataFile, Arrays.asList(infoToAdd), StandardOpenOption.APPEND);
				break;
			case 3:
				System.out.println("Enter contact name: ");
				searchingContact = scanner.nextLine();
				for (int i = 0; i < contactName.size(); i++) {
					if (contactName.get(i).equalsIgnoreCase(searchingContact)) {
						System.out.println("Contact phone number: " + contactNumber.get(i));
						break;
					} else {
						System.out.println("we don't know that person");
					}
				}
				break;
			case 4:
				System.out.println("Enter contact name: ");
				searchingContact = scanner.nextLine();
				scanner.nextLine();
				for (int i = 0; i < contactName.size(); i++) {
					if (contactName.get(i).equalsIgnoreCase(searchingContact)) {
						contactName.remove(i);
						contactNumber.remove(i);
						break;
					} else {
						System.out.println("we don't know that person");
					}
				}
				writeFile(dataFile, contactName, contactNumber);
				break;
				default:
					readFile(dataFile, contactName, contactNumber);
		}

	}

	public static void readFile(Path dataFile, List<String> contactName, List<String> contactNumber) throws IOException {
		List<String> contacts = Files.readAllLines(dataFile);
		for (String contact : contacts) {
			 contactName.add(contact.split(" | ")[0]);
			contactNumber.add(contact.split(" | ")[1]);
		}
	}

	public static void writeFile(Path dataFile, List<String> contactName, List<String> contactNumber) throws IOException {
		String infoToAdd = "";
		for (int i = 0; i < contactName.size(); i++) {
			infoToAdd += contactName.get(i) + " | " + contactNumber.get(i) + "\n";
		}
		Files.write(dataFile, Arrays.asList(infoToAdd), StandardOpenOption.APPEND);
	}


}
