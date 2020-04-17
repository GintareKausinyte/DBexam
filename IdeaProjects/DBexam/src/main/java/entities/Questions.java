package entities;

import javax.persistence.*;

@Entity
@Table(name = "QUESTIONS")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;
    @Column(name = "exam_id")
    private Integer examId;
    @Column(name = "question_text")
    private String questionText;

    public  Questions(){}
    public  Questions(int examId, String questionText){
        this.examId=examId;
        this.questionText=questionText;
    }

    public Questions(Integer questionId, Integer examId, String questionText) {
        this.questionId = questionId;
        this.examId = examId;
        this.questionText = questionText;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}

