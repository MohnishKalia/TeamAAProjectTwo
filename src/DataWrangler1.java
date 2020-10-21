// --== CS400 File Header Information ==--
// Name: Margaret Shen
// Email: mshen42@wisc.edu
// Team: AA
// Role: DataWrangler1
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataWrangler1 {

	/**
	 * Method for reading the members.csv file and stores the information into a red black tree
	 *
	 * @param filePath path of the csv file where the gym data is stored
	 * @return RedBlackTree<Member1> returns a red black tree that contains the
	 *         stored member data
	 */
	public static RedBlackTree<Member1> readFile() {
		String fileName = "members.csv"; // path to the csv file where the member data is read from

		RedBlackTree<Member1> tree = new RedBlackTree<>(); // tree where the data will be stored
		Scanner scnr = null; // reads the csv file
		String csvSplit = ","; // character that the string will be split by
		String line = "";

		try {
			scnr = new Scanner(new FileReader(fileName)); // allows for data reading
			scnr.nextLine(); // gets rid of header
			while (scnr.hasNextLine()) { // reads every line in the csv file
				line = scnr.nextLine();
				String[] data = line.split(csvSplit);
				for (int i = 0; i < data.length; i++) {
					data[i].strip(); // makes sure that any extra whitespace is gotten rid of
				}
				long wiscID = Long.parseLong(data[0]); // converts string to long
				String name = data[1]; // name of member
				String year = data[2].toUpperCase(); // year in school
				Member1 m = new Member1(wiscID, name, Member1.SchoolYear.valueOf(year)); // creates new member object
				tree.insert(m); // inserts the member into the tree
			}
		} catch (Exception e) {
			System.err.println("Error");
			e.printStackTrace();
		} finally {
			if (scnr != null) {
				scnr.close(); // closes scanner
			}
		}
		return tree;
	}

	/**
	 * Helper method for the writeToFile method
	 *
	 * @param writer  file writer
	 * @param gymData tree storing the member objects
	 */
	private static void write(FileWriter writer, RedBlackTree<Member1> gymData) throws IOException {
		writer.write("wiscID,name,year\n"); // header
		String treeData = gymData.toString().replace("[", "").replace("]", ""); // deletes the brackets at the ends of the string
		String[] data = treeData.split(","); // splits string by comma
		// writes the data to the file
		for (int i = 0; i < data.length; i++) {
			data[i].strip(); // makes sure any extra whitespace is removed
			writer.write(data[i]); // writes the data in the file
			if ((i + 1) % 3 == 0) {
				writer.write("\n"); // adds a new line after every 3 entries
				continue;
			}
			if (i == 4){
				continue; // skips the 4th entry (time)
			}
			if (i < data.length) {
				writer.write(","); // adds a comma after every entry except the last one
			}
		}
	}

	/**
	 * Takes a red black tree as a parameter and writes the data into a csv file
	 *
	 * @param gymData tree that contains data to be written into the csv file
	 */
	public static void writeToFile(RedBlackTree<Member1> gymData) {
		String fileName = "members.csv"; // file name

		// defines several objects to be initialized in the following code
		File file = null;
		FileWriter writer = null;
		boolean fileExists = false;

		try {
			file = new File(fileName); // initializes file object
			if (!file.createNewFile()) {
				fileExists = true; // checks if a new file is created
			}
			writer = new FileWriter(file); // initializes the file writer
			write(writer, gymData); // writes to the file
			if (fileExists) { // prints out informative message
				System.out.println(fileName + " successfully updated.");
			} else {
				System.out.println(fileName + " successfully created.");
			}
		} catch (IOException e) {
			System.err.println("Error");
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close(); // closes writer
				}
			} catch (Exception e) {
				System.err.println("Error");
				e.printStackTrace();
			}
		}
	}
}
