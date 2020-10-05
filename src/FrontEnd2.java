import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.List;

public class FrontEnd2 {

    private enum Operation {
        ADD(FrontEnd2::add), REMOVE(FrontEnd2::remove), GET(FrontEnd2::get), PRINTALL(FrontEnd2::printAll),
        SAVE(FrontEnd2::save), EXIT(FrontEnd2::exit);

        private Consumer<Scanner> method;

        private Operation(Consumer<Scanner> method) {
            this.method = method;
        }

        public void exec(Scanner input) {
            method.accept(input);
        }
    }

    public static void main(String[] args) {
        List<String> availableOps = List.of(Operation.values()).stream().map(Operation::name)
                .collect(Collectors.toList());
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.println("Please enter an operation:");
                System.out.println(String.join(", ", availableOps));
                printInputPrefix();
                try {
                    Operation op = Operation.valueOf(input.nextLine());
                    op.exec(input);
                } catch (IllegalArgumentException e) {
                    System.out.println("\n! Invalid Command Entered !");
                }
                System.out.println();
            }
        }
    }

    private static void printInputPrefix() {
        System.out.print("> ");
    }

    private static final State1 state = new State1();

    // Operation Implementations Below

    public static void add(Scanner input) {
        System.out.println("Input WiscID: ");
        printInputPrefix();
        long id = input.nextLong();
        System.out.println(id);
    }
    
    public static void remove(Scanner input) {
        System.out.println("Input WiscID: ");
        printInputPrefix();
        long id = input.nextLong();
        input.nextLine();
        System.out.println(state.removeMember(id));
    }

    public static void get(Scanner input) {
        System.out.println("Input WiscID: ");
        printInputPrefix();
        long id = input.nextLong();
        input.nextLine();
        System.out.println(state.getMember(id));
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
