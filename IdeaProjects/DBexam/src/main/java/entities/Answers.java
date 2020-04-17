package entities;

import javax.persistence.*;

@Entity
@Table(name = "ANSWERS")
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer answerId;
    @Column(name = "question_id")
    private Integer questionId;
    @Column(name = "exam_id")
    private Integer examId;
    @Column(name = "answer_text")
    private String answerText;
    @Column(name = "answer_letter")
    private char answerLetter;
    @Column(name = "correct_answer")
    private boolean isAnswerCorrect;

    public Answers(){}
    public Answers(Integer examId, Integer questionId, String answerText, char answerLetter){
        this.examId=examId;
        this.questionId=questionId;
        this.answerText=answerText;
        this.answerLetter=answerLetter;
    }

    public Answers(Integer answerId, Integer examId, Integer questionId, String answerText, char answerLetter, boolean isAnswerCorrect) {
        this.answerId = answerId;
        this.examId = examId;
        this.questionId = questionId;
        this.answerText = answerText;
        this.answerLetter = answerLetter;
        this.isAnswerCorrect = isAnswerCorrect;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public char getAnswerLetter() {
        return answerLetter;
    }

    public void setAnswerLetter(char answerLetter) {
        this.answerLetter = answerLetter;
    }

    public boolean isAnswerCorrect() {
        return isAnswerCorrect;
    }

    public void setAnswerCorrect(boolean answerCorrect) {
        isAnswerCorrect = answerCorrect;
    }

}
