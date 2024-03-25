package ru.diasoft.dao;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import ru.diasoft.config.ApplicationConfig;
import ru.diasoft.domain.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionDaoImpl implements QuestionDao {
    private final String csvFileName;
    private static final int QUESTION_INDEX = 0;
    private static final int ANSWER_INDEX = 5;

    public QuestionDaoImpl(ApplicationConfig applicationConfig) {
        this.csvFileName = applicationConfig.getCsvFileName();
    }

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile("classpath:" + csvFileName);
            // BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            Scanner scanner;
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                Question question = new Question();
                List<String> answers = new ArrayList<>();
                scanner = new Scanner(line);
                scanner.useDelimiter("/");
                getQuestionOrAnswer(scanner, index, answers, question);
                index = 0;
                question.setAnswers(answers);
                questions.add(question);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return questions;
    }

    private void getQuestionOrAnswer(Scanner scanner, int index, List<String> answers, Question question) {
        while (scanner.hasNext()) {
            String data = scanner.next();
            if (index == QUESTION_INDEX) {
                question.setQuestion(data);
            } else if ((index > QUESTION_INDEX) && (index < ANSWER_INDEX)) {
                answers.add(data);
            } else if (index == ANSWER_INDEX) {
                question.setRightAnswer(Integer.parseInt(data));
            }
            index++;
        }
    }

}