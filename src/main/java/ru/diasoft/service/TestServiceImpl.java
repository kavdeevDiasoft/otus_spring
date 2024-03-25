package ru.diasoft.service;

import org.springframework.stereotype.Service;
import ru.diasoft.config.ApplicationConfig;
import ru.diasoft.domain.Question;
import ru.diasoft.domain.Student;
import ru.diasoft.domain.TestModel;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final IOService ioService;
    private final ApplicationConfig applicationConfig;
    private final MessageSourceService messageSourceService;

    public TestServiceImpl(IOService ioService, ApplicationConfig applicationConfig, MessageSourceService messageSourceService) {
        this.ioService = ioService;
        this.applicationConfig = applicationConfig;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public TestModel createTestModel(Student student, List<Question> questions) {
        return new TestModel(student, questions);
    }

    @Override
    public void run(TestModel testModel) {
        int correctAnswers = testModel.getQuestions().size();
        int currentQuestion = 0;
        for (Question question : testModel.getQuestions()) {
            displayQuestion(currentQuestion, question);
            correctAnswers = getCorrectAnswers(correctAnswers, question);
            currentQuestion++;
        }
        testModel.setResult(correctAnswers >= applicationConfig.getMinRightAnswer());
        displayResult(testModel, correctAnswers);
    }

    private void displayResult(TestModel testModel, int correctAnswers) {
        String testResult = (testModel.isResult()) ? messageSourceService.getMessage("test.passed") : messageSourceService.getMessage("test.notpassed");

        ioService.write(messageSourceService.getMessage("test.end"));
        ioService.write(messageSourceService.getMessage("test.result"));
        ioService.write(messageSourceService.getMessage("test.student") + testModel.getStudent().getSurname() + " " + testModel.getStudent().getFirstname());
        ioService.write(messageSourceService.getMessage("test.questioncount") + testModel.getQuestions().size());
        ioService.write(messageSourceService.getMessage("test.rightanswerscount") + correctAnswers);
        ioService.write(messageSourceService.getMessage("test.test") + testResult);
    }

    private int getCorrectAnswers(int correctAnswers, Question question) {
        boolean flag = false;
        int answerIndex = 0;
        while (!flag) {
            answerIndex = ioService.readInt();
            if (answerIndex < 1 || answerIndex > question.getAnswers().size()) {
                flag = false;
                ioService.write(messageSourceService.getMessage("test.wrongnumber") + question.getAnswers().size());
            } else {
                flag = true;
            }
        }
        if (answerIndex != question.getRightAnswer()) {
            correctAnswers--;
        }
        return correctAnswers;
    }

    private void displayQuestion(int currentQuestion, Question question) {
        ioService.write(messageSourceService.getMessage("test.question") + (currentQuestion + 1) + ": ");
        ioService.write(question.getQuestion());
        for (int i = 1; i <= question.getAnswers().size(); i++) {
            ioService.write(messageSourceService.getMessage("test.answer") + i + ": " + question.getAnswers().get(i - 1));
        }
        ioService.write(messageSourceService.getMessage("test.chooseanswer"));
    }
}
