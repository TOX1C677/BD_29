package Bd29.repository;

import Bd29.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    // Дополнительные методы для поиска, если нужно
    List<Survey> findAllByTitleContaining(String title);  // Поиск анкет по части названия (например, для фильтрации)
}
