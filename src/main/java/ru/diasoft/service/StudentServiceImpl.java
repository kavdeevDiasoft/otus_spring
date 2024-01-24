package ru.diasoft.service;

import ru.diasoft.domain.Student;

public class StudentServiceImpl implements StudentService {
    @Override
    public Student get(String firstname, String surname) {
        return new Student(firstname, surname);
    }

}