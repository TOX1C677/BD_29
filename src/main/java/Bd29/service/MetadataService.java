package Bd29.service;

import Bd29.model.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Bd29.repository.MetadataRepository;

import java.util.List;

@Service
public class MetadataService {

    private final MetadataRepository metadataRepository;

    @Autowired
    public MetadataService(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    // Сохранение метаданных
    public void saveMetadata(Metadata metadata) {
        metadataRepository.save(metadata);
    }

    // Сохранение нескольких метаданных
    public void saveAllMetadata(List<Metadata> metadataList) {
        metadataRepository.saveAll(metadataList);
    }

    // Поиск по ID
    public Metadata getMetadataById(Long id) {
        return metadataRepository.findById(id).orElse(null);
    }

    // Поиск всех метаданных по SurveyId
    public List<Metadata> getMetadataBySurveyId(Long surveyId) {
        return metadataRepository.findAllBySurveyId(surveyId);
    }

    // Подсчет количества метаданных для определенной анкеты
    public long countMetadataBySurveyId(Long surveyId) {
        return metadataRepository.countBySurveyId(surveyId);
    }

    // Обновление метаданных
    public void updateMetadata(Metadata metadata) {
        metadataRepository.save(metadata);  // Для обновления можно просто использовать save, он заменит существующую запись
    }

    // Удаление метаданных по ID
    public void deleteMetadata(Long id) {
        metadataRepository.deleteById(id);
    }

    // Удаление всех метаданных по SurveyId
    public void deleteMetadataBySurveyId(Long surveyId) {
        List<Metadata> metadataList = metadataRepository.findAllBySurveyId(surveyId);
        metadataRepository.deleteAll(metadataList);
    }
}
