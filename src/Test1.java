// --== CS400 File Header Information ==--
// Name: Owen Krueger
// Email: odkrueger@wisc.edu
// Team: AA
// Role: Test Engineer 1
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test1 for Test Engineer 1
 * This class tests the implementation of our application
 */
public class Test1 {

    /**
     * This method tests the creation of a new tree
     * by adding a root node
     */
    @Test
    public void testAddRoot() {
        State1 state = new State1();
        state.insertMember(123456789L, "Granthony", Member1.SchoolYear.SOPHOMORE);
    }

    /**
     * This method tests to make sure members with bad
     * input parameters are not accepted
     */
    @Test
    public void testAddMemberBadInput() {

    }

    /**
     * This method tests to make sure members with good
     * input parameters are added correctly
     */
    @Test
    public void testAddMember() {

    }

    /**
     * This method tests to make sure the CSV file
     * is correctly loaded into the program
     */
    @Test
    public void testCSVImport() {

    }

    /**
     * This method tests to make sure the get()
     * method works properly, and returns the correct
     * member
     */
    @Test
    public void testGet() {

    }
}
