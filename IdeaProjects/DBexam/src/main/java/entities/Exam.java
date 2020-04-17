package entities;

import javax.persistence.*;

@Entity
@Table(name = "EXAM")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Integer examId;
    @Column(name = "exam_name")
    private String examName;

    public Exam(){};

    public Exam(Integer examId, String examName) {
        this.examId = examId;
        this.examName = examName;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}

