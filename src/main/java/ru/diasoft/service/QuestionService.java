package ru.diasoft.service;

import ru.diasoft.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions();

}