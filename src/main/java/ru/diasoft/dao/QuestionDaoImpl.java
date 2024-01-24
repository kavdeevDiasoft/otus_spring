package ru.diasoft.dao;

import org.springframework.util.ResourceUtils;
import ru.diasoft.domain.Question;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionDaoImpl implements QuestionDao {
    private final String filename;
    private static final int QUESTION_INDEX = 0;

    public QuestionDaoImpl(String filename) {
        this.filename = filename;
    }

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile("classpath:" + filename);
            // BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            Scanner scanner;
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                Question question = new Question();

                scanner = new Scanner(line);
                scanner.useDelimiter("/");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == QUESTION_INDEX) {
                        question.setQuestion(data);
                    }
                    index++;
                }
                index = 0;
                questions.add(question);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return questions;
    }

}