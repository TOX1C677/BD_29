package Bd29.controller;

import Bd29.model.Question;
import Bd29.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")  // Указываем общий путь для работы с вопросами
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Получить вопрос по ID
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404 если вопрос не найден
        }
        return ResponseEntity.ok(question);  // 200 если вопрос найден
    }

    // Получить все вопросы для анкеты по SurveyId
    @GetMapping("/survey/{surveyId}")
    public ResponseEntity<List<Question>> getQuestionsBySurveyId(@PathVariable Long surveyId) {
        List<Question> questions = questionService.getQuestionsBySurveyId(surveyId);
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 если вопросов не найдено
        }
        return ResponseEntity.ok(questions);  // 200 если вопросы найдены
    }

    // Подсчитать количество вопросов для анкеты
    @GetMapping("/survey/{surveyId}/count")
    public ResponseEntity<Long> countQuestionsBySurveyId(@PathVariable Long surveyId) {
        long count = questionService.countQuestionsBySurveyId(surveyId);
        return ResponseEntity.ok(count);  // 200 OK с количеством вопросов
    }

    // Сохранить один вопрос
    @PostMapping
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.saveQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);  // 201 если создание прошло успешно
    }

    // Сохранить несколько вопросов
    @PostMapping("/bulk")
    public ResponseEntity<List<Question>> saveAllQuestions(@RequestBody List<Question> questions) {
        List<Question> createdQuestions = questionService.saveAllQuestions(questions);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestions);  // 201 если создание прошло успешно
    }

    // Обновить вопрос
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Question updatedQuestion = questionService.updateQuestion(id, question);
        if (updatedQuestion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404 если вопрос не найден
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedQuestion);  // 200 если обновление прошло успешно
    }

    // Удалить вопрос по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        boolean deleted = questionService.deleteQuestion(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 если вопрос не найден
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 если удаление прошло успешно
    }

    // Удалить все вопросы для анкеты
    @DeleteMapping("/survey/{surveyId}")
    public ResponseEntity<Void> deleteQuestionsBySurveyId(@PathVariable Long surveyId) {
        boolean deleted = questionService.deleteQuestionsBySurveyId(surveyId);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 если нет вопросов для удаления
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 если удаление прошло успешно
    }
}
