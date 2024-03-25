package ru.diasoft.service;

import org.springframework.stereotype.Service;
import ru.diasoft.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public Student get(String firstname, String surname) {
        return new Student(firstname, surname);
    }

}