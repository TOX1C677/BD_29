package Bd29.repository;

import Bd29.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    // Поиск всех опций для конкретного вопроса
    List<Option> findAllByQuestionId(Long questionId);

    // Подсчет количества опций для конкретного вопроса
    long countByQuestionId(Long questionId);
}
