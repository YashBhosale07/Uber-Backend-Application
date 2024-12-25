package in.yash.UberApplication.services.Impl;
import in.yash.UberApplication.dto.DriverDto;
import in.yash.UberApplication.dto.RideDto;
import in.yash.UberApplication.dto.RideRequestDto;
import in.yash.UberApplication.dto.RiderDto;
import in.yash.UberApplication.entities.RideRequest;
import in.yash.UberApplication.entities.Rider;
import in.yash.UberApplication.entities.User;
import in.yash.UberApplication.entities.enums.RideRequestStatus;
import in.yash.UberApplication.repositories.RideRequestRepository;
import in.yash.UberApplication.repositories.RiderRepository;
import in.yash.UberApplication.services.RiderService;
import in.yash.UberApplication.strategies.DriverMatchingStrategy;
import in.yash.UberApplication.strategies.Impl.RideFairDefaultFareCalculationStrategy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RiderServiceImpl implements RiderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RideFairDefaultFareCalculationStrategy rideFairDefaultFareCalculationStrategy;

    @Autowired
    private DriverMatchingStrategy driverMatchingStrategy;

    @Autowired
    private RideRequestRepository rideRequestRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        RideRequest rideRequest=modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        double fare=rideFairDefaultFareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest=rideRequestRepository.save(rideRequest);
        driverMatchingStrategy.findMatchingDriver(rideRequest);
        return modelMapper.map(savedRideRequest,RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getMyAllRides() {
        return null;
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider=Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }
}
