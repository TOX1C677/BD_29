package Bd29.repository;

import Bd29.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // Поиск всех вопросов для конкретной анкеты
    List<Question> findAllBySurveyId(Long surveyId);

    // Подсчет количества вопросов для анкеты
    long countBySurveyId(Long surveyId);
}
