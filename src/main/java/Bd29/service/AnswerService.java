package Bd29.service;

import Bd29.model.Answer;
import Bd29.model.Response;
import Bd29.repository.AnswerRepository;
import Bd29.repository.ResponseRepository;  // Добавим этот репозиторий для поиска Response
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final ResponseRepository responseRepository;  // Для поиска Response

    @Autowired
    public AnswerService(AnswerRepository answerRepository, ResponseRepository responseRepository) {
        this.answerRepository = answerRepository;
        this.responseRepository = responseRepository;
    }

    // Получение всех ответов по ResponseId
    public List<Answer> getAnswersByResponseId(Long responseId) {
        // Сначала ищем объект Response по responseId
        Optional<Response> response = responseRepository.findById(responseId);

        // Если Response не найден, выбрасываем исключение
        if (response.isEmpty()) {
            throw new RuntimeException("Response not found with id " + responseId);
        }

        // Если найден, передаем его в метод findAllByResponse
        return answerRepository.findAllByResponse(response.get());
    }

    // Получение статистики по количеству ответов для анкеты
    public long countAnswersBySurveyId(Long surveyId) {
        return answerRepository.countByResponse_Survey_SurveyId(surveyId);
    }

    // Удаление ответа по ID
    public void deleteAnswer(Long id) {
        if (!answerRepository.existsById(id)) {
            throw new RuntimeException("Answer not found with id " + id);
        }
        answerRepository.deleteById(id);
    }

    // Получение ответа по ID
    public Optional<Answer> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    // Обновление ответа
    public Answer updateAnswer(Answer updatedAnswer) {
        return answerRepository.findById(updatedAnswer.getAnswerId())
                .map(existingAnswer -> {
                    existingAnswer.setResponse(updatedAnswer.getResponse());
                    existingAnswer.setQuestion(updatedAnswer.getQuestion());
                    existingAnswer.setAnswerText(updatedAnswer.getAnswerText());
                    return answerRepository.save(existingAnswer);
                })
                .orElseThrow(() -> new RuntimeException("Answer not found with id " + updatedAnswer.getAnswerId()));
    }
}
