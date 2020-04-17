package database;

import entities.Answers;
import entities.Questions;
import entities.User;
import entities.UserResult;
import menu.MenuInput;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbManagerExam {
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();


    public void dbCreateQuestionsAnswers(String questionText, String answerA, String answerB, String answerC, char correctAnswer, int examId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Questions questions = new Questions(examId, questionText);
            session.save(questions);
            int questionId = questions.getQuestionId();

            Answers answer1 = new Answers(examId, questionId, answerA, 'a');
            Answers answer2 = new Answers(examId, questionId, answerB, 'b');
            Answers answer3 = new Answers(examId, questionId, answerC, 'c');
            settingCorrectAnswerCreateExam(answer1, answer2, answer3, correctAnswer);
            session.save(answer1);
            session.save(answer2);
            session.save(answer3);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    public void settingCorrectAnswerCreateExam(Answers answer1, Answers answer2, Answers answer3, char correctAnswer) {
        if (Character.toUpperCase(correctAnswer) == 'A') {
            answer1.setAnswerCorrect(true);
        } else if (Character.toUpperCase(correctAnswer) == 'B') {
            answer2.setAnswerCorrect(true);
        } else if (Character.toUpperCase(correctAnswer) == 'C') {
            answer3.setAnswerCorrect(true);
        }
    }

    public void dbSaveUserResult(int userId, int examId, boolean isUserInputCorrect, int answerId, char userInputLetter) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserResult userResult = new UserResult(userId, examId, answerId, isUserInputCorrect, userInputLetter);
            session.save(userResult);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

    }

    public void dbSetCorrectAnswerEditExam(int answerId, boolean isCorrect) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Answers answer = session.load(Answers.class, answerId);
            answer.setAnswerCorrect(isCorrect);
            session.save(answer);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

    }

    public void dbUpdateQuestion(int questionIdToEdit, String newQuestionText) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Questions question = session.load(Questions.class, questionIdToEdit);
            question.setQuestionText(newQuestionText);
            session.save(question);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

    }

    public void dbUpdateAnswer(int answerIdToEdit, String newAnswerText) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Answers answer = session.load(Answers.class, answerIdToEdit);
            answer.setAnswerText(newAnswerText);
            session.save(answer);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

    }

    public String dbGetChosenQuestion(int questionId) {
        Session session = factory.openSession();
        Transaction tx = null;
        String questionText = "";
        try {
            tx = session.beginTransaction();
            Questions question = session.load(Questions.class, questionId);
            questionText = question.getQuestionText();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return questionText;
    }

    public String dbGetChosenAnswer(int answerId) {
        Session session = factory.openSession();
        Transaction tx = null;
        String answerText = "";
        try {
            tx = session.beginTransaction();
            Answers answer = session.load(Answers.class, answerId);
            answerText = answer.getAnswerText();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return answerText;
    }

    public List<Questions> dbGetQuestionsList(int examId) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Questions> questionsList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            Query<Questions> query = session.createQuery("FROM Questions WHERE examId=:examId", Questions.class);
            query.setParameter("examId", examId);
            questionsList = query.list();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return questionsList;
    }

    public List<Answers> dbGetAnswersList(int questionId) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Answers> answersList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            Query<Answers> query2 = session.createQuery("FROM Answers WHERE questionId=:questionId", Answers.class);
            query2.setParameter("questionId", questionId);
            answersList = query2.list();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return answersList;
    }

    public int dbUser(String userName) {
        Session session = factory.openSession();
        Transaction tx = null;
        int userId = 0;
        try {
            tx = session.beginTransaction();
            User user = new User(userName);
            session.save(user);
            tx.commit();
            userId = user.getUserId();
            return userId;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return userId;
    }

    public boolean doesAnswerIdExists(int answerId, int questionId) {
        Session session = factory.openSession();
        Transaction tx = null;
        int getAnswerId = 0;
        try {
            Query<Answers> query = session.createQuery("FROM Answers WHERE answerId=:answerId AND questionId=:questionId", Answers.class);
            query.setParameter("answerId", answerId);
            query.setParameter("questionId", questionId);
            List<Answers> answerResult = query.list();
            getAnswerId=answerResult.get(0).getAnswerId();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        if (getAnswerId != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doesQuestionIdExists(int questionId, int examId) {
        Session session = factory.openSession();
        Transaction tx = null;
        int getQuestionId = 0;
        try {
            Query<Questions> query = session.createQuery("FROM Questions WHERE questionId=:questionId AND examId=:examId", Questions.class);
            query.setParameter("examId", examId);
            query.setParameter("questionId", questionId);
            List<Questions> questionsResult = query.list();
            getQuestionId=questionsResult.get(0).getQuestionId();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        if (getQuestionId != 0) {
            return true;
        } else {
            return false;
        }
    }
}

