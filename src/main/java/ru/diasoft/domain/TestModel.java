package ru.diasoft.domain;

import java.util.List;

public class TestModel {
    private Student student;
    private List<Question> questions;
    private boolean result;

    public TestModel(Student student, List<Question> questions) {
        this.student = student;
        this.questions = questions;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
