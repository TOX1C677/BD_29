package Bd29.repository;

import Bd29.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    // Поиск всех ответов для анкеты, используя связь через Survey.id
    List<Response> findAllBySurvey_Id(Long surveyId);

    // Поиск всех ответов по токену
    List<Response> findAllByResponseToken(String responseToken);

    // Подсчет количества ответов для анкеты, используя связь через Survey.id
    long countBySurvey_Id(Long surveyId);
}
