import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

		switch (menuOption) {
			case 1:
				List<String> contacts = Files.readAllLines(dataFile);
				for (String contact : contacts) {
					System.out.println(contact);
				}

		}
	}

}