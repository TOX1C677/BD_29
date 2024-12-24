package Bd29.service;

import Bd29.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Bd29.repository.ResponseRepository;

import java.util.List;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    // Сохранение одного ответа
    public void saveResponse(Response response) {
        responseRepository.save(response);
    }

    // Сохранение нескольких ответов
    public void saveAllResponses(List<Response> responses) {
        responseRepository.saveAll(responses);
    }

    // Поиск по ID
    public Response getResponseById(Long id) {
        return responseRepository.findById(id).orElse(null);
    }

    // Поиск всех ответов для анкеты
    public List<Response> getResponsesBySurveyId(Long surveyId) {
        return responseRepository.findAllBySurvey_Id(surveyId);  // Изменен путь на Survey.id
    }

    // Поиск всех ответов по токену
    public List<Response> getResponsesByResponseToken(String responseToken) {
        return responseRepository.findAllByResponseToken(responseToken);
    }

    // Подсчет количества ответов для анкеты
    public long countResponsesBySurveyId(Long surveyId) {
        return responseRepository.countBySurvey_Id(surveyId);  // Изменен путь на Survey.id
    }

    // Обновление ответа
    public void updateResponse(Response response) {
        responseRepository.save(response);  // Для обновления можно использовать save, он заменит существующую запись
    }

    // Удаление ответа по ID
    public void deleteResponse(Long id) {
        responseRepository.deleteById(id);
    }

    // Удаление всех ответов для анкеты
    public void deleteResponsesBySurveyId(Long surveyId) {
        List<Response> responses = responseRepository.findAllBySurvey_Id(surveyId);  // Изменен путь на Survey.id
        responseRepository.deleteAll(responses);
    }
}
