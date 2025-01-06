package in.yash.UberApplication.controllers;

import in.yash.UberApplication.dto.SignUpDto;
import in.yash.UberApplication.dto.UserDto;
import in.yash.UberApplication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signUp")
    UserDto signUp(@RequestBody SignUpDto signUpDto) {
        return authService.signUp(signUpDto);
    }

}
