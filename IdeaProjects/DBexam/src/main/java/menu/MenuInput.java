package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuInput {
    private Scanner scanner = new Scanner(System.in);

    public int mainMenuInput() {
        System.out.println("Pasirinkite, kokius veiksmus toliau norite atlikti: ");
        System.out.println("1-spręsti egzaminą");
        System.out.println("2-kurti naujus klausimus-atsakymus esamiems egzaminams");
        System.out.println("3-redaguoti esamų egzaminų klausimus/atsakymus");
        System.out.println("4-išjungti programą");
        return userIntInput();
    }

    public int solveExamTopicMenuInput() {
        System.out.println("----EGZAMINAS----");
        System.out.println("Pasirinkite egzamino temą: ");
        System.out.println("1-gamta");
        System.out.println("2-geografija");
        System.out.println("3-matematika");
        return userIntInput();
    }

    public int createExamQuestionsTopicInput() {
        System.out.println("---EGZAMINO KLAUSIMŲ-ATSAKYMŲ KŪRIMAS---");
        System.out.println("Pasirinkite egzaminą, kuriam kursite klausimus-atsakymus: ");
        System.out.println("1-gamta");
        System.out.println("2-geografija");
        System.out.println("3-matematika");
        return userIntInput();
    }

    public int editExamTopicInput() {
        System.out.println("---EGZAMINO KLAUSIMŲ-ATSAKYMŲ REDAGAVIMAS---");
        System.out.println("Pasirinkite egzaminą, kurio klausimus-atsakymus redaguosite: ");
        System.out.println("1-gamta");
        System.out.println("2-geografija");
        System.out.println("3-matematika");
        return userIntInput();
    }

    public String userRegistrationInput() {
        System.out.println("Įrašykite savo vardą: ");
        return userStringNext();
    }

    public void endProgram() {
        System.out.println("Egzaminavimo programa išsijungia. Viso gero");
    }

    public String userStringNext() {
        String userInput = "";
        try {
            userInput = scanner.next();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Neteisinga ivestis");
            userStringInput();
        }
        return userInput;
    }

    public String userStringInput() {
        String userInput = "";
        try {
            userInput = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Neteisinga ivestis");
            userStringInput();
        }
        return userInput;
    }

    public char userCharInput() {
        char userInput = 0;
        try {
            userInput = scanner.next().charAt(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Neteisinga įvestis");
        }
        return userInput;
    }

    public int userIntInput() {
        int userInput = 0;
        try {
            userInput = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Neteisinga įvestis. įveskite skaitmenį");
            userInput = scanner.nextInt();
        }
        return userInput;
    }
}
