package ru.finplatforms.student;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Тестовое задание для АО «Финансовые платформы»"
))
public class StudentApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class);
    }
}
