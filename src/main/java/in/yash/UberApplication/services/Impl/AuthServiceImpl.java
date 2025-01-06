package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.dto.DriverDto;
import in.yash.UberApplication.dto.SignUpDto;
import in.yash.UberApplication.dto.UserDto;
import in.yash.UberApplication.entities.Rider;
import in.yash.UberApplication.entities.User;
import in.yash.UberApplication.entities.enums.Role;
import in.yash.UberApplication.exceptions.RuntimeConflictException;
import in.yash.UberApplication.repositories.UserRepository;
import in.yash.UberApplication.services.AuthService;
import in.yash.UberApplication.services.RiderService;
import in.yash.UberApplication.services.WalletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RiderService riderService;

    @Autowired
    private WalletService walletService;


    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    @Transactional
    public UserDto signUp(SignUpDto signUpDto) {
        User user = userRepository.findByEmail(signUpDto.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeConflictException("Cannot signup, User already exits with email " + signUpDto.getEmail());
        }
        User mappedUser = modelMapper.map(signUpDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        //create user related entities
        Rider rider = riderService.createNewRider(savedUser);
        walletService.createNewWallet(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
