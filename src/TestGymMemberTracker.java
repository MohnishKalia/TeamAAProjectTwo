// --== Test Gym Member Tracker ==--
// Name: Christopher Nguyen
// Email: cnguyen29@wisc.edu
// Team: AA
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.IllegalArgumentException;

public class TestGymMemberTracker{
	/**
	 * Tests importation of the CSV file and
	 * checks for correct members
	 */	
	@Test	
	public void testImportCSV(){
	  State expected = null;
	  expectedMembers(expected);
	  State returned = new State();
	  if (!returned.toString().equals(expected.toString()){
	    fail("Members in file do not match expected members");
	  }
	}
	
	/**
	 * Tests back end exception throws when adding in
	 * an incorrect wisc id 
	 */
	@Test
	public void testAdditionOfMemberWithInvalidID(){
	  State state = new State();
	  try{
	    Member member = new Member(33221,Paul McCartney,SENIOR);
	    fail("Did not throw IllegalArgumentException");
	  }catch(IllegalArgumentException e){
	  }	  
	}

	/**
	 * Tests back end insertMember method with valid
	 * member  
	 */
	@Test
	public void testValidInsertMember(){
	  State state = new State();
	  try{
	    Member member = new Member(123456789,Chuck Berry,SENIOR);
	  }catch(IllegalArgumentException e){
	    fail("Did not insert valid member");
	  }	  
	}




	/**
	 * Tests back end exception throws when getting member
	 * that does not exist in tree 
	 */
	@Test
	public void testRetrievalOfInvalidMember(){
	  State state = new State();
	  try{
	    state.getMember(332215789);
	    fail("Did not throw IllegalArgumentException");
	  }catch(IllegalArgumentException e){
	  }	  
	}

	/**
	 * Tests back end getMember method with valid
	 * member id 
	 */
	@Test
	public void testValidGetMember(){
	  State state = new State();
	  try{
	    Member member = new Member(854148484);
	  }catch(IllegalArgumentException e){
	    fail("Did not find valid member");
	  }	  
	}



	/**
	 * Helper method for inserting expected members
	 * from CSV file
	 */
	private static void expectedMembers(State expected){
	  expected.insertMember(854148484,Johnny Appleseed,FRESHMAN);
	  expected.insertMember(953089646,Paul Blart,JUNIOR);
	  expected.insertMember(103751606,Kristin Yang,SOPHOMORE);
	  expected.insertMember(490885798,Brad Hayes,FRESHMAN);
	  expected.insertMember(840986425,Cherie Corrigan,OTHER);
	  
	}

}
