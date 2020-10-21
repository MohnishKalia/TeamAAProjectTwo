// --== CS400 File Header Information ==--
// Name: Owen Krueger
// Email: odkrueger@wisc.edu
// Team: AA
// Role: Test Engineer 1
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: IMPORTANT: These tests will only pass if the provided
// members.csv file is included

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test1 for Test Engineer 1
 * This class tests the implementation of our application
 * by calling the methods implemented by the State,
 * and checking to see if their outputs match
 * their expected outputs
 *
 * IMPORTANT: These tests will only pass if the provided
 * members.csv file is included
 */
public class Test1 {

        /**
         * This method tests to make sure the CSV file
         * is imported with the proper format
         * It creates a null tree, then fills it
         * with the initial values of the CSV file, and tests
         * to make sure they are equal to a constructed tree
         */
        @Test
        public void testCSVFormat() {
            State1 initial = null;
            fillTree(initial);
            State1 test = new State1();
            assertEquals(initial.toString(), test.toString(), "Members do not match expected members");
        }

        /**
         * This method tests to make sure the tree will
         * accept attempted additions of members with
         * good wiscID
         * No Exceptions should be thrown if
         * a member with a good wiscID is added
         */
        @Test
        public void testAddGoodMember() {
            State1 test = new State1();
            assertDoesNotThrow(() -> test.insertMember(100000009L, "Captain", Member1.SchoolYear.SENIOR));

            assertDoesNotThrow(() -> test.insertMember(999999999L, "Franklin Delano Roosevelt", Member1.SchoolYear.OTHER));

            assertDoesNotThrow(() -> test.insertMember(123456789L, "Happy Gilmore", Member1.SchoolYear.JUNIOR));
        }

        /**
         * This method tests to make sure the tree will
         * not accept attempted additions of members with
         * bad wiscID
         * An IllegalArgumentException should be thrown if
         * a member with a bad wiscID is added
         */
        @Test
        public void testAddBadMember() {
            State1 test = new State1();
            assertThrows(IllegalArgumentException.class,
                    () -> test.insertMember(6090L, "Granthony", Member1.SchoolYear.SOPHOMORE));

            assertThrows(IllegalArgumentException.class,
                    () -> test.insertMember(2345678L, "Gandalf The Grey", Member1.SchoolYear.SENIOR));

            assertThrows(IllegalArgumentException.class,
                    () -> test.insertMember(555551L, "Becky", Member1.SchoolYear.FRESHMAN));
        }

        /**
         * This method tests to make sure the tree
         * will accept attempted getMember() calls
         * with wiscID that are in the tree
         * No exceptions should be thrown if a
         * good wiscID is used with getMember()
         */
        @Test
        public void testGetGoodMember() {
            State1 test = new State1();
            assertDoesNotThrow(() -> test.getMember(854148484L));

            assertDoesNotThrow(() -> test.getMember(953089646L));

            assertDoesNotThrow(() -> test.getMember(103751606L));
        }

        /**
         * This method tests to make sure the tree
         * will not accept attempted getMember() calls
         * with wiscID that are not in the tree
         * IllegalArgumentException should be thrown if
         * a bad wiscID is used with getMember()
         */
        @Test
        public void testGetBadMember() {
            State1 test = new State1();
            assertThrows(IllegalArgumentException.class, () -> test.getMember(162636465L));

            assertThrows(IllegalArgumentException.class, () -> test.getMember(100000005L));

            assertThrows(IllegalArgumentException.class, () -> test.getMember(447733889L));
        }

    /**
     * Private helper method
     * This method inserts all the initial members into the RedBlackTree
     * @param initial The initial RedBlackTree, prior to any changes
     */
    private static void fillTree(State1 initial) {
        initial.insertMember(854148484L, "Johnny Appleseed", Member1.SchoolYear.FRESHMAN);
        initial.insertMember(953089646L, "Paul Blart", Member1.SchoolYear.JUNIOR);
        initial.insertMember(103751606L, "Kristin Yang", Member1.SchoolYear.SOPHOMORE);
        initial.insertMember(490885798L, "Brad Hayes", Member1.SchoolYear.FRESHMAN);
        initial.insertMember(840986425L, "Cherie Corrigan", Member1.SchoolYear.OTHER);
    }
}
