package in.yash.UberApplication.services;

import in.yash.UberApplication.dto.DriverDto;
import in.yash.UberApplication.dto.SignUpDto;
import in.yash.UberApplication.dto.UserDto;

public interface AuthService {

    String login(String email, String password);

    UserDto signUp(SignUpDto signUpDto);

    DriverDto onboardNewDriver(Long userId);

}
