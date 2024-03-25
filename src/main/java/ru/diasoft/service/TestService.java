package ru.diasoft.service;

import ru.diasoft.domain.Question;
import ru.diasoft.domain.Student;
import ru.diasoft.domain.TestModel;

import java.util.List;

public interface TestService {

    TestModel createTestModel(Student student, List<Question> questions);

    void run(TestModel testModel);
}
