package ru.diasoft.service;

import org.junit.jupiter.api.Test;
import ru.diasoft.config.ApplicationConfig;
import ru.diasoft.domain.Question;
import ru.diasoft.domain.Student;
import ru.diasoft.domain.TestModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TestServiceImplTest {
    @Test
    void runTest() {
        int rightAnswer = 1;
        List<Question> questions = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        answers.add("Singer");
        answers.add("Actor");
        answers.add("MMA-Fighter");
        answers.add("Scientist");

        questions.add(new Question("What kind profession had Michael Jackson?", answers, 1));

        Student student = new Student("Ivan", "Ivanov");
        TestModel testModel = new TestModel(student, questions);

        IOService ioServiceMock = mock(IOService.class);
        ApplicationConfig applicationConfig = mock(ApplicationConfig.class);
        MessageSourceService messageSourceService = mock(MessageSourceService.class);

        when(ioServiceMock.read()).thenReturn("");
        when(ioServiceMock.readInt()).thenReturn(rightAnswer);
        doNothing().when(ioServiceMock).write("");

        TestServiceImpl testService = new TestServiceImpl(ioServiceMock, applicationConfig, messageSourceService);

        testService.run(testModel);

        assertTrue(testModel.isResult());
    }

}