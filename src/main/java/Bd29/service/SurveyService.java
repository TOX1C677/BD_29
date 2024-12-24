package Bd29.service;

import Bd29.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Bd29.repository.SurveyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    // Сохранение анкеты
    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    // Поиск анкеты по ID
    public Survey getSurveyById(Long id) {
        return surveyRepository.findById(id).orElse(null);
    }

    // Получение всех анкет
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    // Поиск анкет по части названия
    public List<Survey> getSurveysByTitle(String title) {
        return surveyRepository.findAllByTitleContaining(title);
    }

    // Обновление анкеты
    public Survey updateSurvey(Survey survey) {
        return surveyRepository.save(survey);  // Для обновления можно использовать save, он заменит существующую запись
    }

    // Удаление анкеты
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }
}
