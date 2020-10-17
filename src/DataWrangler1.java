import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataWrangler1 {

	/**
	 * @param filePath path of the csv file where the gym data is stored
	 * @return TreeAndKeys object that stores the red black tree and a list of keys
	 */
	public static TreeAndKeys readFile(String filePath) {
		RedBlackTree<Member> tree = new RedBlackTree<>(); // tree where the data will be stored
		ArrayList<Long> keys = new ArrayList<>(); // array list where the keys will be stored
		BufferedReader csvReader = null; // reads the csv file
		try {
			// creates a csv reader
			csvReader = new BufferedReader(new FileReader(filePath));
			// reads all the data in the file
			while ((csvReader.readLine() != null)) {
				String[] data = csvReader.readLine().split(","); // splits the line by comma
				long wiscID = Long.parseLong(data[0]); // converts string to long
				String name = data[1];
				String year = data[2];
				Member m = new Member(wiscID, name, year); // creates a new member based on the data
				tree.insert(m); // inserts the member into the tree
				keys.add(wiscID); // add the key to the keys array
			}
		} catch (Exception e) {

		} finally {
			if (csvReader != null) {
				try {
					csvReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		// creates a new tree and keys object with the filled tree and keys array
		TreeAndKeys treeAndKeys = new TreeAndKeys(tree, keys);

		return treeAndKeys;
	}

	/**
	 * Helper method for the writeToFile method
	 *
	 * @param writer  file writer
	 * @param gymData tree storing the member objects
	 * @throws IOException
	 */
	public static void write(FileWriter writer, RedBlackTree<Member> gymData) throws IOException {
		String[] data = gymData.toString().split(",");
		for (int i = 0; i < data.length; i++) {
			writer.write(data[i]);
			writer.write("\n");
		}
	}

	public static void writeToFile(String fileName, RedBlackTree<Member> gymData) {
		Scanner sn = new Scanner(System.in);
		File file = null;
		FileWriter writer = null;
		boolean fileExists = false;

		try {
			file = new File(fileName);
			if (!file.createNewFile()) {
				fileExists = true;
			}
			if (fileExists) {
				System.out.println(fileName + " already exists.");
				System.out.println("Do you wish to overwrite " + fileName + " (y/n)?");
				boolean validInput = false;
				String userInput = null;
				while (!validInput) {
					userInput = sn.nextLine();
					if (userInput != null) {
						if (userInput.equals("y") || userInput.equals("n")) {
							validInput = true;
						} else {
							System.out.println("Invalid Input. Enter y or n.");
						}
					}
				}
				if (userInput != null && userInput.equals("y")) {
					writer = new FileWriter(file);
					write(writer, gymData);
					System.out.println(fileName + " successfully overwritten.");
				} else {
					System.out.println(fileName + " was not overwitten.");
				}
			} else {
				writer = new FileWriter(file);
				write(writer, gymData);
				System.out.println(fileName + " successfully created.");
			}
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e) {
			}
			sn.close();
		}
	}
}
