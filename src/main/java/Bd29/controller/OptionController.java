package Bd29.controller;

import Bd29.model.Option;
import Bd29.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")  // Указываем общий путь для работы с опциями
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    // Получить опцию по ID
    @GetMapping("/{id}")
    public ResponseEntity<Option> getOptionById(@PathVariable Long id) {
        Option option = optionService.getOptionById(id);
        if (option == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 если опция не найдена
        }
        return ResponseEntity.ok(option);  // 200 если опция найдена
    }

    // Получить все опции для конкретного вопроса
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Option>> getOptionsByQuestionId(@PathVariable Long questionId) {
        List<Option> options = optionService.getOptionsByQuestionId(questionId);
        if (options.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 если опции не найдены
        }
        return ResponseEntity.ok(options);  // 200 если опции найдены
    }

    // Подсчитать количество опций для конкретного вопроса
    @GetMapping("/question/{questionId}/count")
    public ResponseEntity<Long> countOptionsByQuestionId(@PathVariable Long questionId) {
        long count = optionService.countOptionsByQuestionId(questionId);
        return ResponseEntity.ok(count);  // 200 с количеством опций
    }

    // Сохранить одну опцию
    @PostMapping
    public ResponseEntity<Void> saveOption(@RequestBody Option option) {
        optionService.saveOption(option);
        return ResponseEntity.status(HttpStatus.CREATED).build();  // 201 если создание прошло успешно
    }

    // Сохранить несколько опций
    @PostMapping("/bulk")
    public ResponseEntity<Void> saveAllOptions(@RequestBody List<Option> options) {
        optionService.saveAllOptions(options);
        return ResponseEntity.status(HttpStatus.CREATED).build();  // 201 если сохранение прошло успешно
    }

    // Обновить опцию
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOption(@PathVariable Long id, @RequestBody Option option) {
        Option existingOption = optionService.getOptionById(id);
        if (existingOption == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 если опция не найдена
        }
        option.setOptionId(id);  // Устанавливаем ID для обновления существующей записи
        optionService.updateOption(option);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 если обновление прошло успешно
    }

    // Удалить опцию по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        Option option = optionService.getOptionById(id);
        if (option == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 если опция не найдена
        }
        optionService.deleteOption(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 если удаление прошло успешно
    }

    // Удалить все опции для конкретного вопроса
    @DeleteMapping("/question/{questionId}")
    public ResponseEntity<Void> deleteOptionsByQuestionId(@PathVariable Long questionId) {
        optionService.deleteOptionsByQuestionId(questionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 если удаление прошло успешно
    }
}
