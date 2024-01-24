package ru.diasoft.service;

import ru.diasoft.domain.Question;
import ru.diasoft.domain.Student;

import java.util.List;

public class RunServiceImpl implements RunService {
    private final StudentService studentService;
    private final QuestionService questionService;
    private final IOService ioService;

    public RunServiceImpl(StudentService studentService, QuestionService questionService, IOService ioService) {
        this.studentService = studentService;
        this.questionService = questionService;
        this.ioService = ioService;
    }

    @Override
    public void run() {
        ioService.write("Enter your name");
        String firstname = ioService.read();
        ioService.write("Enter your surname");
        String surname = ioService.read();

        Student student = studentService.get(firstname, surname);
        List<Question> questions = questionService.getQuestions();
        System.out.println("Hello " + student.getFirstname() + " " + student.getSurname() + "!");
        for (Question question : questions) {
            System.out.println(question.getQuestion());
        }
    }

}