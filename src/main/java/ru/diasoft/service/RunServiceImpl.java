package ru.diasoft.service;

import org.springframework.stereotype.Service;
import ru.diasoft.domain.Question;
import ru.diasoft.domain.Student;
import ru.diasoft.domain.TestModel;

import java.util.List;

@Service
public class RunServiceImpl implements RunService {
    private final StudentService studentService;
    private final QuestionService questionService;
    private final IOService ioService;
    private final TestServiceImpl testService;
    private final MessageSourceService messageSourceService;


    public RunServiceImpl(StudentService studentService, QuestionService questionService, IOService ioService, TestServiceImpl testService, MessageSourceService messageSourceService) {
        this.studentService = studentService;
        this.questionService = questionService;
        this.ioService = ioService;
        this.testService = testService;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public void run() {
        ioService.write(messageSourceService.getMessage("person.name"));
        String firstname = ioService.read();
        ioService.write(messageSourceService.getMessage("person.surname"));
        String surname = ioService.read();

        Student student = studentService.get(firstname, surname);
        List<Question> questions = questionService.getQuestions();
        System.out.println("Hello " + student.getFirstname() + " " + student.getSurname() + "!");
        TestModel testModel = testService.createTestModel(student, questions);
        testService.run(testModel);
    }

}