package com.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.dao.UserDao;
import com.springboot.model.User;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(UserDao userDao){
        return args -> {
            User user1 = new User();
            user1.setFirstName("Nilesh");
            user1.setLastName("More");
            user1.setSalary(12345);
            user1.setAge(23);
            user1.setUserName("nilesh");
            user1.setPassword("nilesh");
            userDao.save(user1);

            User user2 = new User();
            user2.setFirstName("Shivraj");
            user2.setLastName("Jadhav");
            user2.setSalary(4567);
            user2.setAge(34);
            user2.setUserName("shivraj");
            user2.setPassword("shivraj");
            userDao.save(user2);
        };
    }

}
