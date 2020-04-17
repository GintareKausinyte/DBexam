package menu;

import database.DbManagerExam;
import exam.CreateExam;
import exam.EditExam;
import exam.SolveExam;

public class Menu {
    private MenuInput menuInput=new MenuInput();
    private DbManagerExam dbManager=new DbManagerExam();
    private EditExam editExam=new EditExam();
    private CreateExam createExam=new CreateExam();
    private SolveExam solveExam=new SolveExam();


    public int mainMenuRun(){
        int mainMenuChoice=menuInput.mainMenuInput();
        switch (mainMenuChoice){
            case 1:
                 int userId=userRegistration();
                 int choice=menuInput.solveExamTopicMenuInput();
                 int validChoice=examTopicChoiceValidator(choice);
                 solveExam.solveExam(validChoice,userId);
                break;
            case 2:
                int choice1=menuInput.createExamQuestionsTopicInput();
                int validChoice1=examTopicChoiceValidator(choice1);
                createExam.createQuestionsAnswers(validChoice1);
                break;
            case 3:
                int choice2=menuInput.editExamTopicInput();
                int validChoice2=examTopicChoiceValidator(choice2);
                editExam.editExam(validChoice2);
                break;
//            case 4:
//                //viewStatistics
            case 4:
                menuInput.endProgram();
                System.exit(0);
                break;
            default:
                System.err.println("Pasirinkite 1-5!");
                break;
        }
        return mainMenuChoice;
    }
    private int examTopicChoiceValidator(int choice){
        while(true) {
            if(choice==1||choice==2||choice==3) {
             return choice;
            }
            System.err.println("Pasirinkite 1-3!");
            choice = menuInput.userIntInput();
        }
    }

    private int userRegistration(){
        String userName= menuInput.userRegistrationInput();
        return dbManager.dbUser(userName.toUpperCase());
    }
}
