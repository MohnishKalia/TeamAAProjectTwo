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
	public static RedBlackTree<Member1> readFile() {
		String fileName = "members.csv";

		RedBlackTree<Member1> tree = new RedBlackTree<>(); // tree where the data will be stored
		Scanner scnr = null; // reads the csv file
		String csvSplit = ",";
		String line = "";

		try {
			scnr = new Scanner(new FileReader(fileName)); // allows for data reading
			scnr.nextLine(); // gets rid of header

			while (scnr.hasNextLine()) {
				line = scnr.nextLine();
				String[] data = line.split(csvSplit);

				for (int i = 0; i < data.length; i++) {
					data[i].strip();
				}

				long wiscID = Long.parseLong(data[0]); // converts string to long
				String name = data[1]; // name of member
				String year = data[2].toUpperCase(); // year in school

				Member1 m = new Member1(wiscID, name, Member1.SchoolYear.valueOf(year));
				tree.insert(m); // inserts the member into the tree
				keys.add(wiscID); // add the key to the keys array
			}
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		} finally {
			if (scnr != null) {
				scnr.close();
			}
		}

		return tree;
	}

	/**
	 * Helper method for the writeToFile method
	 *
	 * @param writer  file writer
	 * @param gymData tree storing the member objects
	 * @throws IOException
	 */
	public static void write(FileWriter writer, RedBlackTree<Member1> gymData) throws IOException {
		writer.write("wiscID,name,year\n"); // header
		String treeData = gymData.toString().replace("[", "").replace("]", "");
		String[] data = treeData.split(",");
		for (int i = 0; i < data.length; i++) {
			data[i].strip();
			writer.write(data[i]);
			if ((i + 1) % 3 == 0) {
				writer.write("\n");
				continue;
			}
			if (i < data.length) {
				writer.write(",");
			}
		}
	}

	public static void writeToFile(RedBlackTree<Member1> gymData) {
		String fileName = "members.csv";

		File file = null;
		FileWriter writer = null;
		boolean fileExists = false;

		try {
			file = new File(fileName);
			if (!file.createNewFile()) {
				fileExists = true;
			}
			writer = new FileWriter(file);
			write(writer, gymData);
			if (fileExists) {
				System.out.println("members.csv successfully updated.");
			} else {
				System.out.println("members.csv successfully created.");
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
		}
	}
}
