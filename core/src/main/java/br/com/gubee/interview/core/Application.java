package br.com.gubee.interview.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Application {

//    public static void main(String[] args) {
//
//        SpringApplication.run(Application.class, args);
//    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
