package Bd29.model;

import jakarta.persistence.*;

@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    @ManyToOne // Связь с сущностью Question
    @JoinColumn(name = "questionId") // Указываем имя столбца для связи с таблицей Question
    private Question question;

    private String optionText;

    // Конструкторы
    public Option() {}

    public Option(Long optionId, Question question, String optionText) {
        this.optionId = optionId;
        this.question = question;
        this.optionText = optionText;
    }

    // Геттеры и сеттеры
    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
