package exam;

import database.DbManagerExam;
import entities.Answers;
import entities.Questions;
import menu.MenuInput;

import java.util.List;

public class EditExam {
    private DbManagerExam dbManager = new DbManagerExam();
    private MenuInput menuInput = new MenuInput();

    public void editExam(int examId) {
        List<Questions> questionsResults = dbManager.dbGetQuestionsList(examId);
        System.out.println("Įrašykite klausimo id numerį, kurio tekstą norite redaguoti: ");
        for (int i = 0; i < questionsResults.size(); i++) {
            System.out.println("ID- " + questionsResults.get(i).getQuestionId() + ": " + questionsResults.get(i).getQuestionText());
        }
        int questionIdToEdit;
        while (true) {
            questionIdToEdit = menuInput.userIntInput();
            if (dbManager.doesQuestionIdExists(questionIdToEdit, examId)) {
                String oldQuestionText = dbManager.dbGetChosenQuestion(questionIdToEdit);
                System.out.println("Jūs pasirinkote klausimą: " + oldQuestionText);
                System.out.println("Įrašykite naują klausimo tekstą: ");
                menuInput.userStringInput();
                String newQuestionText = menuInput.userStringInput();
                dbManager.dbUpdateQuestion(questionIdToEdit, newQuestionText);
                break;
            } else {
                System.out.println("Toks klausimo id numeris neegzistuoja. Bandykite dar kartą:");
            }
        }

        while (true) {
            System.out.println("Ar norite redaguoti šio klausimo atsakymo variantus?\n1-Taip\n2-Ne");
            int userInput = menuInput.userIntInput();
            if (userInput == 1) {
                editAnswers(questionIdToEdit);
            } else if (userInput == 2) {
                System.out.println("Ar norite pakeisti redaguoto klausimo teisingo atsakymo variantą?\n1-Taip\n2-Ne");
               int userInput1=menuInput.userIntInput();
               if(userInput1==1) {
                   userCorrectAnswerChoice(questionIdToEdit);
               }else if(userInput1==2) {
                   System.out.println("----Klausimų-atsakymų redagavimas baigtas----");
                   System.out.println("");
                   break;
               }else {
                   System.out.println("Įvedėte neteisingą skaičių");
               }
            } else {
                System.out.println("Bandykite dar karta. Įvedėte neteisingą skaičių: ");
            }
        }
    }

    private void editAnswers(int questionId) {
        List<Answers> answersResults = dbManager.dbGetAnswersList(questionId);
        System.out.println("Irašykite atsakymo id numerį, kurio tekstą norite redaguoti: ");
        for (Answers a : answersResults) {
            int answerId = a.getAnswerId();
            String answerText = a.getAnswerText();
            char answerLetter = a.getAnswerLetter();
            System.out.println(answerLetter + ")" + "ID- " + answerId + ": " + answerText);
        }
        while (true) {
            int answerIdToEdit = menuInput.userIntInput();

            if (dbManager.doesAnswerIdExists(answerIdToEdit, questionId)) {
                String answerChoice = dbManager.dbGetChosenAnswer(answerIdToEdit);
                System.out.println("Jūs pasirinkote: " + answerChoice);
                System.out.println("Įrašykite naują pilną atsakymo tekstą: ");
                menuInput.userStringInput();
                String newAnswerText = menuInput.userStringInput();
                dbManager.dbUpdateAnswer(answerIdToEdit, newAnswerText);
                break;
            } else {
                System.out.println("Toks atsakymo id numeris neegzistuoja. Bandykite dar kartą: ");
            }
        }
    }

    private void userCorrectAnswerChoice(int questionId) {
        List<Answers> answersResults = dbManager.dbGetAnswersList(questionId);
        while (true) {
            System.out.println("Išrinkite naują teisingą atsakymą A-B-C:");
            char correctAnswerLetter = menuInput.userCharInput();
            if (Character.toUpperCase(correctAnswerLetter) == 'A' || Character.toUpperCase(correctAnswerLetter) == 'B' || Character.toUpperCase(correctAnswerLetter) == 'C') {
                updatingCorrectAndIncorrectAnswersEditExam(answersResults, correctAnswerLetter);
                break;
            }
        }
    }


    private void updatingCorrectAndIncorrectAnswersEditExam(List<Answers> answersResults, char correctAnswerLetter) {
        if (Character.toUpperCase(correctAnswerLetter) == 'A') {
            int answerId0 = answersResults.get(0).getAnswerId();
            int answerId1 = answersResults.get(1).getAnswerId();
            int answerId2 = answersResults.get(2).getAnswerId();
            dbManager.dbSetCorrectAnswerEditExam(answerId0, true);
            dbManager.dbSetCorrectAnswerEditExam(answerId1, false);
            dbManager.dbSetCorrectAnswerEditExam(answerId2, false);
        } else if (Character.toUpperCase(correctAnswerLetter) == 'B') {
            int answerId0 = answersResults.get(0).getAnswerId();
            int answerId1 = answersResults.get(1).getAnswerId();
            int answerId2 = answersResults.get(2).getAnswerId();
            dbManager.dbSetCorrectAnswerEditExam(answerId0, false);
            dbManager.dbSetCorrectAnswerEditExam(answerId1, true);
            dbManager.dbSetCorrectAnswerEditExam(answerId2, false);
        } else if (Character.toUpperCase(correctAnswerLetter) == 'C') {
            int answerId0 = answersResults.get(0).getAnswerId();
            int answerId1 = answersResults.get(1).getAnswerId();
            int answerId2 = answersResults.get(2).getAnswerId();
            dbManager.dbSetCorrectAnswerEditExam(answerId0, false);
            dbManager.dbSetCorrectAnswerEditExam(answerId1, false);
            dbManager.dbSetCorrectAnswerEditExam(answerId2, true);
        }
    }
}
