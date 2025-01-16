package in.yash.UberApplication.controllers;

import in.yash.UberApplication.dto.*;
import in.yash.UberApplication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(authService.signUp(signUpDto));
    }

    @PostMapping("/onBoardNewDriver")
    public ResponseEntity<DriverDto>onBoardNewDriver(@RequestBody OnBoardNewDriverDto onBoardNewDriverDto){
        return new ResponseEntity<>(authService.onboardNewDriver(onBoardNewDriverDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<loginResponseDto>login(@RequestBody loginRequestDto loginRequestDto){
        String[] tokens= authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        return ResponseEntity.ok(new loginResponseDto(tokens[0],tokens[1]));
    }



}
