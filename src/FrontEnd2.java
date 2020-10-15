// --== CS400 File Header Information ==--
// Name: Mohnish Kalia
// Email: mkalia2@wisc.edu
// Team: AA
// Role: Front End 2
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: 

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.EnumSet;

/**
 * Front End 2 implementation for project 2
 * 
 * @author Mohnish Kalia
 */
public class FrontEnd2 {

    /**
     * This enum is used to model the different operations the front end performs.
     * Each of the enum constants take in a method reference that will get ran with
     * a Scanner whenever the user desires to use that operation.
     */
    private enum Operation {

        ADD(FrontEnd2::add), REMOVE(FrontEnd2::remove), GET(FrontEnd2::get), PRINTALL(FrontEnd2::printAll),
        SAVE(FrontEnd2::save), EXIT(FrontEnd2::exit);

        private Consumer<Scanner> method;

        /**
         * @param method the method reference to a static method that will run whenever
         *               exec is called
         */
        private Operation(Consumer<Scanner> method) {
            this.method = method;
        }

        /**
         * @param input the Scanner that is used for user input or closing the input
         *              stream
         */
        public void exec(Scanner input) {
            method.accept(input);
        }

    }

    /**
     * Prints the PS2
     * (https://www.gnu.org/savannah-checkouts/gnu/bash/manual/bash.html#:~:text=PS2)
     * when the user is prompted for input
     */
    private static void printInputPrefix() {
        System.out.print("> ");
    }

    /**
     * Gets the formatted error string for a given error message
     * 
     * @param message the error message you wish to format
     * @return the message with a new line at the beginning and surrounded with
     *         exclamation points
     */
    private static String getErrorString(String message) {
        return String.format("\n! %s !", message);
    }

    private static final String INVALID_INPUT_MESSAGE = "Invalid Input Field";
    private static final String WISC_ID_PROMPT = "Input WiscID:";

    /**
     * Runs a block of code while appropriately handling errors from both user input
     * and state operations
     * 
     * @param codeBlock block of code to run under try-catch
     */
    private static void runWithCatch(Runnable codeBlock) {
        try {
            codeBlock.run();
        } catch (IllegalArgumentException e) {
            // user input was malformed
            System.out.println(getErrorString(INVALID_INPUT_MESSAGE));
        } catch (Exception e) {
            // TODO: Add StateException here instead of generic exception
            // user input was correctly formed, but is not valid
            System.out.println(getErrorString(e.getMessage()));
        }
    }

    public static void main(String[] args) {
        // try-with-resources
        // (https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html)
        try (Scanner input = new Scanner(System.in)) {
            // loop is infinite because program should be canceled with the EXIT method
            // (^C and Stop button on IDE will terminate the program completely)
            while (true) {
                runWithCatch(() -> {
                    System.out.println("Please enter an operation:");
                    // print out the set of enum constants for the user
                    System.out.println(EnumSet.allOf(Operation.class));
                    printInputPrefix();
                    // will throw IllegalArgumentException if the user input is not an enum constant
                    Operation op = Operation.valueOf(input.nextLine().toUpperCase());
                    // runs the associated method, passing in the Scanner for reuse
                    op.exec(input);
                });
                // line separator to make output more readable
                System.out.println();
            }
        }
    }

    // Enum Operation Implementations Below

    /**
     * The state instance that is used for the operations
     */
    private static final State1 state = new State1();

    public static void add(Scanner input) {
        runWithCatch(() -> {
            System.out.println(WISC_ID_PROMPT);
            printInputPrefix();
            long wiscID = Long.parseLong(input.nextLine());

            System.out.println("Input Name:");
            printInputPrefix();
            String name = input.nextLine();

            System.out.println("Input School Year:");
            // print out the set of enum constants for the user
            System.out.println(EnumSet.allOf(Member.SchoolYear.class));
            printInputPrefix();
            Member.SchoolYear yearInSchool = Member.SchoolYear.valueOf(input.nextLine().toUpperCase());

            state.insertMember(wiscID, name, yearInSchool);
            System.out.println("Member inserted!");
        });
    }

    public static void remove(Scanner input) {
        runWithCatch(() -> {
            System.out.println(WISC_ID_PROMPT);
            printInputPrefix();
            long wiscID = Long.parseLong(input.nextLine());

            state.removeMember(wiscID);
            System.out.println("Member removed!");
        });
    }

    public static void get(Scanner input) {
        runWithCatch(() -> {
            System.out.println(WISC_ID_PROMPT);
            printInputPrefix();
            long wiscID = Long.parseLong(input.nextLine());

            System.out.println(state.getMember(wiscID));
        });
    }

    public static void printAll(Scanner input) {
        System.out.println(state);
    }

    public static void save(Scanner input) {
        System.out.println("State saved!");
        state.save();
    }

    public static void exit(Scanner input) {
        System.out.println("Goodbye!");
        input.close();
        System.exit(0);
    }

}
