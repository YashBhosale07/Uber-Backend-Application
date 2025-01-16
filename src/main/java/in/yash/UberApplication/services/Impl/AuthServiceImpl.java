package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.Security.JwtService;
import in.yash.UberApplication.dto.DriverDto;
import in.yash.UberApplication.dto.OnBoardNewDriverDto;
import in.yash.UberApplication.dto.SignUpDto;
import in.yash.UberApplication.dto.UserDto;
import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Rider;
import in.yash.UberApplication.entities.User;
import in.yash.UberApplication.entities.enums.Role;
import in.yash.UberApplication.exceptions.ResourceNotFoundException;
import in.yash.UberApplication.exceptions.RuntimeConflictException;
import in.yash.UberApplication.repositories.UserRepository;
import in.yash.UberApplication.services.AuthService;
import in.yash.UberApplication.services.DriverService;
import in.yash.UberApplication.services.RiderService;
import in.yash.UberApplication.services.WalletService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static in.yash.UberApplication.entities.enums.Role.DRIVER;

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

    @Autowired
    private DriverService driverService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public String[] login(String email, String password) {
       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(email,password);
       Authentication authentication=authenticationManager.authenticate(usernamePasswordAuthenticationToken);
       User user= (User) authentication.getPrincipal();
       jwtService.generateAcessToken(user);
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
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        //create user related entities
        Rider rider = riderService.createNewRider(savedUser);
        walletService.createNewWallet(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(OnBoardNewDriverDto onBoardNewDriverDto) {
        User user=userRepository.findById(onBoardNewDriverDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User is not present with id "+onBoardNewDriverDto.getUserId()));
        if(user.getRoles().contains(DRIVER)) throw new RuntimeConflictException("User with id "+onBoardNewDriverDto.getUserId()+" is already a driver");
        Driver driver=new Driver();
        driver.setUser(user);
        driver.setVehicleNumber(onBoardNewDriverDto.getVehicleNumber());
        driver.setAvailable(true);
        driver.setDrivingLicenceNumber(onBoardNewDriverDto.getDrivingLicenceNumber());
        driver.setAddress(onBoardNewDriverDto.getAddress());
        driver.setMobile(onBoardNewDriverDto.getMobile());
        Coordinate coordinate=new Coordinate(onBoardNewDriverDto.getCurrentLocation().getCoordinates()[0],onBoardNewDriverDto.getCurrentLocation().getCoordinates()[1]);
        GeometryFactory geometryFactory=new GeometryFactory();
        Point point=geometryFactory.createPoint(coordinate);
        driver.setCurrentLocation(point);
        user.getRoles().add(DRIVER);
        userRepository.save(user);
        Driver savedDriver=driverService.createNewDriver(driver);
        return modelMapper.map(driver,DriverDto.class);
    }
}
