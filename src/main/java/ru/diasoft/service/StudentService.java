package ru.diasoft.service;

import ru.diasoft.domain.Student;

public interface StudentService {
    Student get(String firstname, String surname);
}
