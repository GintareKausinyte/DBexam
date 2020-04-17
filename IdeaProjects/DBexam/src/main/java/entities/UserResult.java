package entities;

import javax.persistence.*;

@Entity
@Table(name = "USER_RESULT")
public class UserResult {
        @Id
        @Column(name = "user_id")
        private Integer userId;
        @Column(name = "exam_id")
        private Integer examId;
        @Column(name = "user_answer_id")
        private Integer userAnswerId;
        @Column(name = "user_answ_correct")
        private boolean isUserAnswerCorrect;
        @Column(name = "user_answer_letter")
        private char userAnswerLetter;

    public UserResult(Integer userId, Integer examId, Integer userAnswerId, boolean isUserAnswerCorrect, char userAnswerLetter) {
        this.userId = userId;
        this.examId = examId;
        this.userAnswerId = userAnswerId;
        this.isUserAnswerCorrect = isUserAnswerCorrect;
        this.userAnswerLetter = userAnswerLetter;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getUserAnswerId() {
        return userAnswerId;
    }

    public void setUserAnswerId(Integer userAnswerId) {
        this.userAnswerId = userAnswerId;
    }

    public boolean isUserAnswerCorrect() {
        return isUserAnswerCorrect;
    }

    public void setUserAnswerCorrect(boolean userAnswerCorrect) {
        isUserAnswerCorrect = userAnswerCorrect;
    }

    public char getUserAnswerLetter() {
        return userAnswerLetter;
    }

    public void setUserAnswerLetter(char userAnswerLetter) {
        this.userAnswerLetter = userAnswerLetter;
    }
}

