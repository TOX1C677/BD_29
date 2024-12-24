package Bd29.model;

import jakarta.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne // Связь с сущностью Survey
    @JoinColumn(name = "surveyId") // Указываем имя столбца для связи с таблицей Survey
    private Survey survey;

    private String text;
    private String type;

    // Конструкторы
    public Question() {}

    public Question(Long questionId, Survey survey, String text, String type) {
        this.questionId = questionId;
        this.survey = survey;
        this.text = text;
        this.type = type;
    }

    // Геттеры и сеттеры
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
