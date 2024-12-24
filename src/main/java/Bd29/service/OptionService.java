package Bd29.service;

import Bd29.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Bd29.repository.OptionRepository;

import java.util.List;

@Service
public class OptionService {

    private final OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    // Сохранение одной опции
    public void saveOption(Option option) {
        optionRepository.save(option);
    }

    // Сохранение нескольких опций
    public void saveAllOptions(List<Option> options) {
        optionRepository.saveAll(options);
    }

    // Поиск по ID
    public Option getOptionById(Long id) {
        return optionRepository.findById(id).orElse(null);
    }

    // Поиск всех опций для конкретного вопроса
    public List<Option> getOptionsByQuestionId(Long questionId) {
        return optionRepository.findAllByQuestionId(questionId);
    }

    // Подсчет количества опций для конкретного вопроса
    public long countOptionsByQuestionId(Long questionId) {
        return optionRepository.countByQuestionId(questionId);
    }

    // Обновление опции
    public void updateOption(Option option) {
        optionRepository.save(option);  // Для обновления можно просто использовать save, он заменит существующую запись
    }

    // Удаление опции по ID
    public void deleteOption(Long id) {
        optionRepository.deleteById(id);
    }

    // Удаление всех опций для конкретного вопроса
    public void deleteOptionsByQuestionId(Long questionId) {
        List<Option> options = optionRepository.findAllByQuestionId(questionId);
        optionRepository.deleteAll(options);
    }
}
