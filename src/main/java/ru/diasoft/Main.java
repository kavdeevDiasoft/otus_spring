package ru.diasoft;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.diasoft.service.RunService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        RunService runService = context.getBean(RunService.class);
        runService.run();
        context.close();
    }
}