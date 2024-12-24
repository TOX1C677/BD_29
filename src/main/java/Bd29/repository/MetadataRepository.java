package Bd29.repository;

import Bd29.model.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, Long> {

    // Поиск всех метаданных по SurveyId
    List<Metadata> findAllBySurveyId(Long surveyId);

    // Подсчет количества метаданных для определенной анкеты
    long countBySurveyId(Long surveyId);
}
