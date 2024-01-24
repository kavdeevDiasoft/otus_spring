package ru.diasoft.service;

import org.junit.jupiter.api.Test;
import ru.diasoft.dao.QuestionDaoImpl;
import ru.diasoft.domain.Question;
import ru.diasoft.domain.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RunServiceImplTest {
    @Test
    void runTest() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Who created the periodic table of elements?"));
        questions.add(new Question("Surname of the black ex-president of the United States?"));
        questions.add(new Question("Year of the start of the Second World War?"));
        questions.add(new Question("Country with the largest territory?"));
        questions.add(new Question("What was Newton's name?"));

        Student student = new Student("Ivan", "Ivanov");

        IOService ioServiceMock = mock(IOService.class);
        when(ioServiceMock.read()).thenReturn("");
        doNothing().when(ioServiceMock).write("");

        QuestionService questionService = new QuestionServiceImpl(new QuestionDaoImpl("questions.csv"));

        StudentService studentServiceMock = mock(StudentService.class);
        when(studentServiceMock.get(anyString(), anyString())).thenReturn(student);

        RunServiceImpl runService = new RunServiceImpl(studentServiceMock, questionService, ioServiceMock);
        runService.run();
        assertDoesNotThrow(() -> new Exception());
        assertEquals(questions, questionService.getQuestions());
    }

}