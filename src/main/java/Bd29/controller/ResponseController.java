package Bd29.controller;

import Bd29.model.Response;
import Bd29.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responses")  // Указываем общий путь для работы с ответами
public class ResponseController {

    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    // Получить ответ по ID
    @GetMapping("/{id}")
    public ResponseEntity<Response> getResponseById(@PathVariable Long id) {
        Response response = responseService.getResponseById(id);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 если ответ не найден
        }
        return ResponseEntity.ok(response);  // 200 если ответ найден
    }

    // Получить все ответы для анкеты по SurveyId
    @GetMapping("/survey/{surveyId}")
    public ResponseEntity<List<Response>> getResponsesBySurveyId(@PathVariable Long surveyId) {
        List<Response> responses = responseService.getResponsesBySurveyId(surveyId);
        if (responses.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 если ответов не найдено
        }
        return ResponseEntity.ok(responses);  // 200 если ответы найдены
    }

    // Получить все ответы по токену
    @GetMapping("/token/{responseToken}")
    public ResponseEntity<List<Response>> getResponsesByResponseToken(@PathVariable String responseToken) {
        List<Response> responses = responseService.getResponsesByResponseToken(responseToken);
        if (responses.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 если ответов с таким токеном не найдено
        }
        return ResponseEntity.ok(responses);  // 200 если ответы найдены
    }

    // Подсчитать количество ответов для анкеты
    @GetMapping("/survey/{surveyId}/count")
    public ResponseEntity<Long> countResponsesBySurveyId(@PathVariable Long surveyId) {
        long count = responseService.countResponsesBySurveyId(surveyId);
        return ResponseEntity.ok(count);  // 200 OK с количеством ответов
    }

    // Сохранить один ответ
    @PostMapping
    public ResponseEntity<Void> saveResponse(@RequestBody Response response) {
        responseService.saveResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).build();  // 201 если создание прошло успешно
    }

    // Сохранить несколько ответов
    @PostMapping("/bulk")
    public ResponseEntity<Void> saveAllResponses(@RequestBody List<Response> responses) {
        responseService.saveAllResponses(responses);
        return ResponseEntity.status(HttpStatus.CREATED).build();  // 201 если создание прошло успешно
    }

    // Обновить ответ
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateResponse(@PathVariable Long id, @RequestBody Response response) {
        Response existingResponse = responseService.getResponseById(id);
        if (existingResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 если ответ не найден
        }
        response.setResponseId(id);  // Устанавливаем ID для обновления существующего ответа
        responseService.updateResponse(response);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 если обновление прошло успешно
    }

    // Удалить ответ по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        Response response = responseService.getResponseById(id);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 если ответ не найден
        }
        responseService.deleteResponse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 если удаление прошло успешно
    }

    // Удалить все ответы для анкеты
    @DeleteMapping("/survey/{surveyId}")
    public ResponseEntity<Void> deleteResponsesBySurveyId(@PathVariable Long surveyId) {
        responseService.deleteResponsesBySurveyId(surveyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 если удаление прошло успешно
    }
}
