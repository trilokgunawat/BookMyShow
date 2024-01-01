package com.example.bookmyshow_november.Controller;

import com.example.bookmyshow_november.Service.UserService;
import com.example.bookmyshow_november.dtos.SignUpUserRequestDto;
import com.example.bookmyshow_november.dtos.SignUpUserResponseDto;
import com.example.bookmyshow_november.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpUserResponseDto signUpUSer(SignUpUserRequestDto request){
        User user = userService.signUpUser(request.getEmail(), request.getPassword());
        SignUpUserResponseDto response = new SignUpUserResponseDto();
        response.setUserId(user.getId());
        return response;
    }
}
