package Bd29.controller;

import Bd29.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import Bd29.service.SurveyService;

import java.util.List;

@Controller
public class MainController {

    private final SurveyService surveyService;

    @Autowired
    public MainController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    // Отображаем страницу с анкетами
    @GetMapping("/surveys")
    public String showSurveys(Model model) {
        // Получаем все анкеты из сервиса
        List<Survey> surveys = surveyService.getAllSurveys();
        // Добавляем анкеты в модель, чтобы передать их в шаблон
        model.addAttribute("surveys", surveys);
        return "surveys";  // Название HTML-шаблона (surveys.html)
    }
}
