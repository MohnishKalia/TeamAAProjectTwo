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
 * by calling the methods implemented by the State,
 * and checking to see if their outputs match
 * their expected outputs
 */
public class Test1 {

    /**
     * This method tests the creation of a new tree
     * by adding a root node
     * It tests to make sure the tree contains the member added
     * as the root node, and nothing else
     */
    @Test
    public void testAddRoot() {
        try {
            State1 state = new State1();
            long wiscID = 123456789;
            String name = "Granthony";
            Member1.SchoolYear year = Member1.SchoolYear.SOPHOMORE;
            Member1 member = new Member1(wiscID, name, year);
            state.insertMember(wiscID, name, year);
            assertEquals(member.toString(), state.toString());
        }
        catch (Exception e) {
            System.out.println("Unanticipated Exception Caught");
            fail();
        }
    }

    /**
     * This method tests to make sure members with bad
     * input parameters are not accepted
     * One good member is added, then two bad ones. This method
     * checks to make sure the tree only contains the good member
     * after attempting to add the two bad ones.
     */
    @Test
    public void testAddMemberBadInput() {
        State1 state = new State1();
        long wiscID = 123456789;
        String name = "Granthony";
        Member1.SchoolYear year = Member1.SchoolYear.SOPHOMORE;
        Member1 member = new Member1(wiscID, name, year);
        state.insertMember(wiscID, name, year);

        try {
            wiscID = 24;
            name = "Franklin";
            year = Member1.SchoolYear.FRESHMAN;
            state.insertMember(wiscID, name, year);
        }
        catch(IllegalArgumentException e) {
            assertEquals(member.toString(), state.toString());
        }

        try {
            wiscID = 33333333;
            name = "Kevin Malone";
            year = Member1.SchoolYear.OTHER;
            state.insertMember(wiscID, name, year);
        }
        catch(IllegalArgumentException e) {
            assertEquals(member.toString(), state.toString());
        }
        catch (Exception e) {
            System.out.println("Unanticipated Exception Caught");
            fail();
        }
    }

    /**
     * This method tests to make sure members with good
     * input parameters are added correctly
     * Adds 7 members, and makes sure the concantenation of
     * the member.toString() is equal to the state.toString()
     */
    @Test
    public void testAddMember() {
        State1 state = new State1();
        long wiscID = 123456700;
        String name = "Name: ";
        Member1.SchoolYear year = Member1.SchoolYear.SOPHOMORE;
        String expected = "";
        Member1 member = new Member1(wiscID, name, year);
        for (int i = 0; i < 7; i++) {
            state.insertMember(wiscID + i, name + i, year);
            expected += "\n" + new Member1(wiscID + i, name + i, year).toString();
        }
        assertEquals(expected.trim(), state.toString().trim());
    }

    /**
     * This method tests to make sure the CSV file
     * is correctly loaded into the program
     * It creates a tree, adds 7 members to it, and then saves it
     * Then it creates a new state without adding any members,
     * and tests to see if they are equal
     */
    @Test
    public void testCSVImport() {
        State1 state = new State1();
        long wiscID = 123456700;
        String name = "Name: ";
        Member1.SchoolYear year = Member1.SchoolYear.SOPHOMORE;
        String expected = "";
        Member1 member = new Member1(wiscID, name, year);
        for (int i = 0; i < 7; i++) {
            state.insertMember(wiscID + i, name + i, year);
            expected += "\n" + new Member1(wiscID + i, name + i, year).toString();
        }
        state.save();
        State1 afterSave = new State1();
        assertEquals(state.toString().trim(), afterSave.toString().trim());
    }

    /**
     * This method tests to make sure the get()
     * method works properly, and returns the correct
     * member
     * It adds 7 acceptable members to the tree, then
     * calls the getMember() method on two of them.
     * It also calls the getMember() method on a member
     * that is not in the tree.
     * If the members returned are not correct, or if an error
     * is not thrown after the third call, this test fails
     */
    @Test
    public void testGetMember() {
        State1 state = new State1();
        long wiscID = 123456700;
        String name = "Name: ";
        Member1.SchoolYear year = Member1.SchoolYear.SOPHOMORE;
        String expected = "";
        Member1 member = new Member1(wiscID, name, year);
        for (int i = 0; i < 7; i++) {
            state.insertMember(wiscID + i, name + i, year);
            expected += "\n" + new Member1(wiscID + i, name + i, year).toString();
        }
        Member1 memA = state.getMember(123456704);
        assertEquals(memA.toString().substring(0,9), "123456704");

        Member1 memB = state.getMember(123456701);
        assertEquals(memB.toString().substring(0,9), "123456701");

        try {
            Member1 memC = state.getMember(123456735);
            fail();
        }
        catch(IllegalArgumentException e) {

        }
        catch(Exception e) {
            System.out.println("Unanticipated Exception Caught");
            fail();
        }
    }
}
