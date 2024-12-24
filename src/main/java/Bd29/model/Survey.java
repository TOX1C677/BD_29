package Bd29.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  // Заменить на jakarta.persistence.Id

import java.time.LocalDateTime;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;

    private String title;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    // Конструкторы
    public Survey() {}

    public Survey(String title, String description, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        setExpiresAt(expiresAt);  // Используем сеттер с проверкой
    }

    // Геттеры и сеттеры
    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        // Валидация: дата окончания не может быть раньше даты создания
        if (expiresAt != null && expiresAt.isBefore(this.createdAt)) {
            throw new IllegalArgumentException("Expiration date cannot be before creation date.");
        }
        this.expiresAt = expiresAt;
    }
}


