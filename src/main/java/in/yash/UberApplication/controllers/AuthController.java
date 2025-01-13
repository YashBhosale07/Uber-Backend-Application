package in.yash.UberApplication.controllers;

import in.yash.UberApplication.dto.DriverDto;
import in.yash.UberApplication.dto.OnBoardNewDriverDto;
import in.yash.UberApplication.dto.SignUpDto;
import in.yash.UberApplication.dto.UserDto;
import in.yash.UberApplication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
