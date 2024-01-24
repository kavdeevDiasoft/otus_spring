package ru.diasoft.dao;

import ru.diasoft.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();

}
