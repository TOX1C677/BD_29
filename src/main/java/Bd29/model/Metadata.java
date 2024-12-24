package Bd29.model;

import jakarta.persistence.*;

@Entity
public class Metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metadataId;

    @ManyToOne
    @JoinColumn(name = "surveyId", nullable = false)  // Убедитесь, что связь обязательная
    private Survey survey;

    private String key;
    private String value;

    // Конструкторы
    public Metadata() {}

    public Metadata(Survey survey, String key, String value) {
        this.survey = survey;
        this.key = key;
        this.value = value;
    }

    // Геттеры и сеттеры
    public Long getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(Long metadataId) {
        this.metadataId = metadataId;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
