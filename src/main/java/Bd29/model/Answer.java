package Bd29.model;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация первичного ключа
    private Long answerId;

    @ManyToOne // Связь с сущностью Response
    @JoinColumn(name = "responseId") // Указываем имя столбца для связи с таблицей Response
    private Response response;

    @ManyToOne // Связь с сущностью Question
    @JoinColumn(name = "questionId") // Указываем имя столбца для связи с таблицей Question
    private Question question;

    private String answerText;

    // Конструкторы
    public Answer() {}

    public Answer(Long answerId, Response response, Question question, String answerText) {
        this.answerId = answerId;
        this.response = response;
        this.question = question;
        this.answerText = answerText;
    }

    // Геттеры и сеттеры
    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
