package Bd29.controller;

import Bd29.model.Survey;
import Bd29.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")  // Указываем общий путь для работы с анкетами
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    // Получить анкету по ID
    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id) {
        Survey survey = surveyService.getSurveyById(id);
        if (survey == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404 если анкета не найдена
        }
        return ResponseEntity.ok(survey);  // 200 если анкета найдена
    }

    // Получить все анкеты
    @GetMapping
    public ResponseEntity<List<Survey>> getAllSurveys() {
        List<Survey> surveys = surveyService.getAllSurveys();
        if (surveys.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 если анкеты не найдены
        }
        return ResponseEntity.ok(surveys);  // 200 если анкеты найдены
    }

    // Поиск анкет по части названия
    @GetMapping("/search")
    public ResponseEntity<List<Survey>> getSurveysByTitle(@RequestParam String title) {
        List<Survey> surveys = surveyService.getSurveysByTitle(title);
        if (surveys.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 если анкеты по части названия не найдены
        }
        return ResponseEntity.ok(surveys);  // 200 если анкеты найдены
    }

    // Создание анкеты
    @PostMapping
    public ResponseEntity<Survey> saveSurvey(@RequestBody Survey survey) {
        Survey createdSurvey = surveyService.saveSurvey(survey);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSurvey);  // 201 если анкета создана
    }

    // Обновление анкеты
    @PutMapping("/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody Survey survey) {
        Survey existingSurvey = surveyService.getSurveyById(id);
        if (existingSurvey == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 если анкета не найдена
        }
        survey.setSurveyId(id);  // Устанавливаем ID для обновления существующей анкеты
        Survey updatedSurvey = surveyService.updateSurvey(survey);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSurvey);  // 200 если анкета обновлена
    }

    // Удаление анкеты по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        Survey survey = surveyService.getSurveyById(id);
        if (survey == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 если анкета не найдена
        }
        surveyService.deleteSurvey(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 если удаление прошло успешно
    }
}
