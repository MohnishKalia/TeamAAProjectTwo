// --== Test Gym Member Tracker ==--
// Name: Christopher Nguyen
// Email: cnguyen29@wisc.edu
// Role: Test Engineer 2
// Team: AA
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the implementation of the State1 class
 */
public class Test2{
	/**
	 * Tests importation of the CSV file and
	 * checks for correct members
	 */	
	@Test	
	public void testImportCSV(){
	  State1 expected = null;
	  expectedMembers(expected);
	  State1 returned = new State1();
	  assertEquals(expected.toString(),returned.toString(),"Members do not match expected members");
	  }
	}
	
	/**
	 * Tests back end exception throws when adding in
	 * an incorrect wisc id 
	 */
	@Test
	public void testAdditionOfMemberWithInvalidID(){
	  State1 state = new State1();
	  assertThrows(IllegalArgumentException.class,()->state.insertMember(221L,"Paul McCartney",Member1.SchoolYear.SENIOR));	  
	}

	/**
	 * Tests back end insertMember method with valid
	 * member  
	 */
	@Test
	public void testValidInsertMember(){
	  State1 state = new State1();
	  assertDoesNotThrow(()->state.insertMember(123456789L,"Chuck Berry",Member1.SchoolYear.SENIOR));	  
	}




	/**
	 * Tests back end exception throws when getting member
	 * that does not exist in tree 
	 */
	@Test
	public void testRetrievalOfInvalidMember(){
	  State1 state = new State1();
	  assertThorws(IllegalArgumentException.class,()->state.getMember(332215789L)); 
	}

	/**
	 * Tests back end getMember method with valid
	 * member id 
	 */
	@Test
	public void testValidGetMember(){
	  State1 state = new State1();
	  assertDoesNotThrow(()->state.getMember(854148484L));   
	}



	/**
	 * Helper method for inserting expected members
	 * from CSV file
	 */
	private static void expectedMembers(State expected){
	  expected.insertMember(854148484L,"Johnny Appleseed",Member1.SchoolYear.FRESHMAN);
	  expected.insertMember(953089646L,"Paul Blart",Member1.SchoolYear.JUNIOR);
	  expected.insertMember(103751606L,"Kristin Yang",Member1.SchoolYear.SOPHOMORE);
	  expected.insertMember(490885798L,"Brad Hayes",Member1.SchoolYear.FRESHMAN);
	  expected.insertMember(840986425L,"Cherie Corrigan",Member1.SchoolYear.OTHER);
	  
	}

}
