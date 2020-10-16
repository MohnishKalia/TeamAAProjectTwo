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

        ADD(FrontEnd2::add), GET(FrontEnd2::get), PRINTALL(FrontEnd2::printAll), SAVE(FrontEnd2::save),
        EXIT(FrontEnd2::exit);

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

    private static final String INVALID_INPUT_MESSAGE = "Invalid Input Field";
    private static final String INVALID_WISC_ID_MESSAGE = "Provided WiscID was not a number";
    private static final String INVALID_ENUM_CONSTANT_MESSAGE_SUFFIX = "is not a valid choice";
    private static final String WISC_ID_PROMPT = "Input WiscID:";

    public static void main(String[] args) {
        // try-with-resources
        // (https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html)
        try (Scanner input = new Scanner(System.in)) {
            // loop is infinite because program should be canceled with the EXIT method
            // (^C and Stop button on IDE will terminate the program completely)
            while (true) {
                runWithErrorHandling(() -> {
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

    /**
     * Prints the PS2
     * (https://www.gnu.org/savannah-checkouts/gnu/bash/manual/bash.html#:~:text=PS2)
     * when the user is prompted for input
     */
    private static void printInputPrefix() {
        System.out.print("> ");
    }

    /**
     * Gets the formatted error string for a given error message surrounded with a
     * given string
     * 
     * @param surrounder the String that will surround the message
     * @param message    the error message you wish to format
     * @return the message with a new line at the beginning and surrounded with the
     *         surrounder string
     */
    private static String getErrorString(String surrounder, String message) {
        return String.format("\n%s %s %s", surrounder, message, surrounder);
    }

    /**
     * Runs a block of code while appropriately handling errors from both user input
     * and state operations
     * 
     * @param codeBlock block of code to run under try-catch
     */
    private static void runWithErrorHandling(Runnable codeBlock) {
        try {
            codeBlock.run();
        } catch (NumberFormatException e) {
            // error was from an invalid parse to long (NumberFormatException),
            // so show a more accurate error message
            System.out
                    .println(getErrorString("!", INVALID_INPUT_MESSAGE) + getErrorString("~", INVALID_WISC_ID_MESSAGE));
        } catch (IllegalArgumentException e) {
            // user input was correctly formed but not valid, or is not valid enum choice

            // storing in local variable for reuse
            String errorMessage = e.getMessage();
            // get the array of parts from the error message to extract the last element
            // (user input) later if invalid enum choice
            String[] parts = errorMessage.split("\\.");
            // if the error was from an invalid enum constant choice,
            // then show a more accurate error message
            System.out.println(getErrorString("!", INVALID_INPUT_MESSAGE) + getErrorString("~",
                    errorMessage.contains("enum constant")
                            ? String.format("%s %s", parts[parts.length - 1], INVALID_ENUM_CONSTANT_MESSAGE_SUFFIX)
                            : errorMessage));
        }
    }

    // *** Enum Operation Implementations Below ***

    /**
     * The state instance that is used for the operations
     */
    private static final State1 state = new State1();

    public static void add(Scanner input) {
        runWithErrorHandling(() -> {
            System.out.println(WISC_ID_PROMPT);
            printInputPrefix();
            long wiscID = Long.parseLong(input.nextLine());

            System.out.println("Input Name:");
            printInputPrefix();
            String name = input.nextLine();

            System.out.println("Input School Year:");
            // print out the set of enum constants for the user
            System.out.println(EnumSet.allOf(Member1.SchoolYear.class));
            printInputPrefix();
            Member1.SchoolYear yearInSchool = Member1.SchoolYear.valueOf(input.nextLine().toUpperCase());

            state.insertMember(wiscID, name, yearInSchool);
            System.out.println("Member inserted!");
        });
    }

    public static void get(Scanner input) {
        runWithErrorHandling(() -> {
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
