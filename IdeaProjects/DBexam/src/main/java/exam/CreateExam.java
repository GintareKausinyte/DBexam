package exam;

import database.DbManagerExam;
import menu.MenuInput;

public class CreateExam {
    private DbManagerExam dbManager=new DbManagerExam();
    private MenuInput menuInput=new MenuInput();

    public void createQuestionsAnswers(int examId) {
        System.out.println("Sukurkite klausimą: ");
        String questionText = menuInput.userStringInput();
        System.out.println("Sukurkite A atsakymo variantą: ");
        String answerTextA = menuInput.userStringInput();
        System.out.println("Sukurkite B atsakymo variantą: ");
        String answerTextB = menuInput.userStringInput();
        System.out.println("Sukurkite C atsakymo variantą: ");
        String answerTextC = menuInput.userStringInput();
        while (true) {
            System.out.println("Įrašykite teisingo atsakymo raidę A-B-C: ");
            char correctAnswer = menuInput.userCharInput();
            if (Character.toUpperCase(correctAnswer) == 'A' || Character.toUpperCase(correctAnswer) == 'B' || Character.toUpperCase(correctAnswer) == 'C') {
                dbManager.dbCreateQuestionsAnswers(questionText, answerTextA, answerTextB, answerTextC, correctAnswer, examId);
                System.out.println("---Klausimų-atsakymų kūrimas baigtas---");
                System.out.println("");
                break;
            }
        }
    }
}
