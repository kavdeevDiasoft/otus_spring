package ru.diasoft.service;

import org.springframework.stereotype.Service;
import ru.diasoft.dao.QuestionDao;
import ru.diasoft.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestions() {
        return questionDao.getQuestions();
    }

}