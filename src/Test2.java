// --== Test 2==--
// Name: Christopher Nguyen
// Email: cnguyen29@wisc.edu
// Role: Test Engineer 2
// Team: AA
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * This class tests the implementation of the State1 class
 */
public class Test2 {
	/**
	 * Tests importation of the CSV file and checks for empty file
	 */
	@Test
	public void testImportCSV() {
		State1 returned = new State1();
		assertNotEquals(returned.toString(), "", "Members do not match expected members");
	}

	/**
	 * Tests back end exception throws when adding in an incorrect wisc id
	 */
	@Test
	public void testAdditionOfMemberWithInvalidID() {
		State1 state = new State1();
		assertThrows(IllegalArgumentException.class,
				() -> state.insertMember(221L, "Paul McCartney", Member1.SchoolYear.SENIOR));
	}

	/**
	 * Tests back end insertMember method with valid member
	 */
	@Test
	public void testValidInsertMember() {
		State1 state = new State1();
		assertDoesNotThrow(() -> state.insertMember(123456789L, "Chuck Berry", Member1.SchoolYear.SENIOR));
	}

	/**
	 * Tests back end exception throws when getting member that does not exist in
	 * tree
	 */
	@Test
	public void testRetrievalOfInvalidMember() {
		State1 state = new State1();
		assertThrows(IllegalArgumentException.class, () -> state.getMember(332215789L));
	}

	/**
	 * Tests back end getMember method with valid member id
	 */
	@Test
	public void testValidGetMember() {
		State1 state = new State1();
		assertDoesNotThrow(() -> state.getMember(854148484L));
	}

}
