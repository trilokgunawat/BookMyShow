package com.example.bookmyshow_november;

import com.example.bookmyshow_november.Controller.UserController;
import com.example.bookmyshow_november.dtos.SignUpUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class BookMyShowNovemberApplication  {
//    private UserController userController;
//    @Autowired
//    public BookMyShowNovemberApplication(UserController userController) {
//        this.userController = userController;
//    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowNovemberApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        SignUpUserRequestDto request = new SignUpUserRequestDto();
//        request.setEmail("trilokgunawat@gmail.com");
//        request.setPassword("password");
//
//        userController.signUpUSer(request);
//
//    }
}
