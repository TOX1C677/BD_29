package Bd29.service;

import Bd29.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Bd29.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // Сохранение одного вопроса
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);  // Возвращаем сохраненный объект
    }

    // Сохранение нескольких вопросов
    public List<Question> saveAllQuestions(List<Question> questions) {
        return questionRepository.saveAll(questions);  // Возвращаем список сохраненных вопросов
    }

    // Поиск по ID
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);  // Возвращаем вопрос по ID или null, если не найден
    }

    // Поиск всех вопросов для анкеты
    public List<Question> getQuestionsBySurveyId(Long surveyId) {
        return questionRepository.findAllBySurveyId(surveyId);  // Получаем все вопросы по ID анкеты
    }

    // Подсчет количества вопросов для анкеты
    public long countQuestionsBySurveyId(Long surveyId) {
        return questionRepository.countBySurveyId(surveyId);  // Подсчитываем количество вопросов по ID анкеты
    }

    // Обновление вопроса
    public Question updateQuestion(Long id, Question question) {
        // Проверяем, существует ли вопрос с таким ID
        Question existingQuestion = questionRepository.findById(id).orElse(null);
        if (existingQuestion != null) {
            question.setQuestionId(id);  // Устанавливаем ID для обновления существующего вопроса
            return questionRepository.save(question);  // Сохраняем обновленный вопрос
        }
        return null;  // Возвращаем null, если вопрос не найден
    }

    // Удаление вопроса по ID
    public boolean deleteQuestion(Long id) {
        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);  // Удаляем вопрос по ID
            return true;  // Возвращаем true, если удаление прошло успешно
        }
        return false;  // Возвращаем false, если вопрос с таким ID не найден
    }

    // Удаление всех вопросов для анкеты
    public boolean deleteQuestionsBySurveyId(Long surveyId) {
        List<Question> questions = questionRepository.findAllBySurveyId(surveyId);
        if (!questions.isEmpty()) {
            questionRepository.deleteAll(questions);  // Удаляем все вопросы для анкеты
            return true;  // Возвращаем true, если удаление прошло успешно
        }
        return false;  // Возвращаем false, если вопросы для анкеты не найдены
    }
}
