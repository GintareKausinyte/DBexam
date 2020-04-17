package exam;

import database.DbManagerExam;
import entities.Answers;
import entities.Questions;
import menu.MenuInput;

import java.util.List;

public class SolveExam {
    DbManagerExam dbManager = new DbManagerExam();
    MenuInput menuInput = new MenuInput();

    public void solveExam(int examId, int userId) {
        System.out.println("----Egzaminas pradedamas----");
        List<Questions> questionsResults = dbManager.dbGetQuestionsList(examId);
        if (!questionsResults.isEmpty()) {

            for (int i = 0; i < questionsResults.size(); i++) {
                String question = questionsResults.get(i).getQuestionText();
                System.out.println(i+1+ ". " + question);
                int questionId = questionsResults.get(i).getQuestionId();
                List<Answers> answersResults = dbManager.dbGetAnswersList(questionId);

                String answerOptionA = answersResults.get(0).getAnswerText();
                String answerOptionB = answersResults.get(1).getAnswerText();
                String answerOptionC = answersResults.get(2).getAnswerText();
                System.out.println("a) " + answerOptionA +
                        "\nb) " + answerOptionB +
                        "\nc) " + answerOptionC);
                char userInput;
                while (true) {
                    System.out.println("Pasirinkite variantą A-B-C:");
                    userInput = menuInput.userCharInput();
                    if (Character.toUpperCase(userInput) == 'A' | Character.toUpperCase(userInput) == 'B' | Character.toUpperCase(userInput) == 'C') {
                        checkingIsAnswerCorrectSolveExam(answersResults, userInput, userId, examId);
                        break;
                    }
                }

            }
        } else {
            System.err.println("Egzaminas neturi klausimų!");
        }
        System.out.println("----Egzaminas baigtas----");
        System.out.println("");
    }

    private void checkingIsAnswerCorrectSolveExam(List<Answers> answersResults, char userInput, int userId, int examId) {
        int userAnswerId;
        boolean isUserAnswerCorrect;
        if (Character.toUpperCase(userInput) == 'A') {
            userAnswerId = answersResults.get(0).getAnswerId();
            isUserAnswerCorrect = answersResults.get(0).isAnswerCorrect();
            dbManager.dbSaveUserResult(userId, examId, isUserAnswerCorrect, userAnswerId, Character.toUpperCase(userInput));
        } else if (Character.toUpperCase(userInput) == 'B') {
            userAnswerId = answersResults.get(1).getAnswerId();
            isUserAnswerCorrect = answersResults.get(0).isAnswerCorrect();
            dbManager.dbSaveUserResult(userId, examId, isUserAnswerCorrect, userAnswerId, Character.toUpperCase(userInput));
        } else if (Character.toUpperCase(userInput) == 'C') {
            userAnswerId = answersResults.get(2).getAnswerId();
            isUserAnswerCorrect = answersResults.get(0).isAnswerCorrect();
            dbManager.dbSaveUserResult(userId, examId, isUserAnswerCorrect, userAnswerId, Character.toUpperCase(userInput));
        }

    }

}
