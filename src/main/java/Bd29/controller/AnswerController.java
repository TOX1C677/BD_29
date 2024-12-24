package Bd29.controller;

import Bd29.model.Answer;
import Bd29.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // Получение всех ответов по ResponseId
    @GetMapping("/response/{responseId}")
    public List<Answer> getAnswersByResponseId(@PathVariable Long responseId) {
        return answerService.getAnswersByResponseId(responseId);
    }

    // Получение статистики по количеству ответов для анкеты
    @GetMapping("/survey/{surveyId}/count")
    public long countAnswersBySurveyId(@PathVariable Long surveyId) {
        return answerService.countAnswersBySurveyId(surveyId);
    }

    // Удаление ответа по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long id) {
        try {
            answerService.deleteAnswer(id);
            return ResponseEntity.ok("Answer deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Получение ответа по ID
    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id) {
        return answerService.getAnswerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Обновление ответа
    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody Answer updatedAnswer) {
        updatedAnswer.setAnswerId(id);
        try {
            Answer updated = answerService.updateAnswer(updatedAnswer);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
