package Bd29.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responseId;

    @ManyToOne
    @JoinColumn(name = "surveyId", nullable = false) // Указываем внешний ключ для связи с Survey
    private Survey survey;

    private String responseToken;

    private LocalDateTime submittedAt;

    // Конструктор без параметров для JPA
    public Response() {}

    // Конструктор с параметрами для удобства создания объектов
    public Response(Survey survey, String responseToken, LocalDateTime submittedAt) {
        this.survey = survey;
        setResponseToken(responseToken);  // Валидация токена
        setSubmittedAt(submittedAt);  // Валидация даты
    }

    // Геттеры и сеттеры
    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Long getSurveyId() {
        return (survey != null) ? survey.getSurveyId() : null;
    }

    public String getResponseToken() {
        return responseToken;
    }

    public void setResponseToken(String responseToken) {
        // Валидация: responseToken не может быть пустым
        if (responseToken == null || responseToken.trim().isEmpty()) {
            throw new IllegalArgumentException("Response token cannot be null or empty.");
        }
        this.responseToken = responseToken;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        // Валидация: дата не может быть в будущем
        if (submittedAt != null && submittedAt.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Submitted date cannot be in the future.");
        }
        this.submittedAt = submittedAt;
    }
}
