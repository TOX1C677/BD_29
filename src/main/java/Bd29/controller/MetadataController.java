package Bd29.controller;

import Bd29.model.Metadata;
import Bd29.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metadata")  // Маппинг для всех запросов к этому контроллеру
public class MetadataController {

    private final MetadataService metadataService;

    @Autowired
    public MetadataController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    // Получить метаданные по ID
    @GetMapping("/{id}")
    public ResponseEntity<Metadata> getMetadataById(@PathVariable Long id) {
        Metadata metadata = metadataService.getMetadataById(id);
        if (metadata == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404, если не найдено
        }
        return ResponseEntity.ok(metadata);  // 200, если найдено
    }

    // Получить все метаданные для определенной анкеты
    @GetMapping("/survey/{surveyId}")
    public ResponseEntity<List<Metadata>> getMetadataBySurveyId(@PathVariable Long surveyId) {
        List<Metadata> metadataList = metadataService.getMetadataBySurveyId(surveyId);
        if (metadataList.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204, если метаданные не найдены
        }
        return ResponseEntity.ok(metadataList);  // 200, если метаданные найдены
    }

    // Подсчет метаданных для анкеты
    @GetMapping("/survey/{surveyId}/count")
    public ResponseEntity<Long> countMetadataBySurveyId(@PathVariable Long surveyId) {
        long count = metadataService.countMetadataBySurveyId(surveyId);
        return ResponseEntity.ok(count);  // 200 с количеством метаданных
    }

    // Сохранить метаданные
    @PostMapping
    public ResponseEntity<Void> saveMetadata(@RequestBody Metadata metadata) {
        metadataService.saveMetadata(metadata);
        return ResponseEntity.status(HttpStatus.CREATED).build();  // 201, если создание прошло успешно
    }

    // Сохранить несколько метаданных
    @PostMapping("/bulk")
    public ResponseEntity<Void> saveAllMetadata(@RequestBody List<Metadata> metadataList) {
        metadataService.saveAllMetadata(metadataList);
        return ResponseEntity.status(HttpStatus.CREATED).build();  // 201, если сохранение прошло успешно
    }

    // Обновить метаданные
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMetadata(@PathVariable Long id, @RequestBody Metadata metadata) {
        Metadata existingMetadata = metadataService.getMetadataById(id);
        if (existingMetadata == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404, если метаданные не найдены
        }
        metadata.setMetadataId(id);  // Устанавливаем ID для обновления существующей записи
        metadataService.updateMetadata(metadata);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204, если обновление прошло успешно
    }

    // Удалить метаданные по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetadata(@PathVariable Long id) {
        Metadata metadata = metadataService.getMetadataById(id);
        if (metadata == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404, если метаданные не найдены
        }
        metadataService.deleteMetadata(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204, если удаление прошло успешно
    }

    // Удалить все метаданные по SurveyId
    @DeleteMapping("/survey/{surveyId}")
    public ResponseEntity<Void> deleteMetadataBySurveyId(@PathVariable Long surveyId) {
        metadataService.deleteMetadataBySurveyId(surveyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204, если удаление прошло успешно
    }
}
