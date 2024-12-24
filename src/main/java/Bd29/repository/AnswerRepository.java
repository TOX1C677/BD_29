package Bd29.repository;

import Bd29.model.Answer;
import Bd29.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    // Поиск всех ответов по объекту Response
    List<Answer> findAllByResponse(Response response);  // Теперь используем объект Response вместо responseId

    // Количество ответов для определенной анкеты
    long countByResponse_Survey_SurveyId(Long surveyId);  // Исправлено, чтобы считать по связанным сущностям
}
