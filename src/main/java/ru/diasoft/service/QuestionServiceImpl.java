package ru.diasoft.service;

import ru.diasoft.dao.QuestionDao;
import ru.diasoft.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getQuestions() {
        return questionDao.getQuestions();
    }

}