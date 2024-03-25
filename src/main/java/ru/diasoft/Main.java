package ru.diasoft;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.diasoft.service.RunService;

@Configuration
@ComponentScan
@PropertySource("classpath:app.properties")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        RunService runService = context.getBean(RunService.class);
        runService.run();
        context.close();
    }
}