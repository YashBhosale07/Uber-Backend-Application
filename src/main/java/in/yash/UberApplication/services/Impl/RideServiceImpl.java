package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.dto.RideRequestDto;
import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.RideRequest;
import in.yash.UberApplication.entities.enums.RideRequestStatus;
import in.yash.UberApplication.entities.enums.RideStatus;
import in.yash.UberApplication.exceptions.ResourceNotFoundException;
import in.yash.UberApplication.repositories.RideRepository;
import in.yash.UberApplication.services.RideRequestService;
import in.yash.UberApplication.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;
    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(()->new ResourceNotFoundException("Ride not found with id "+rideId));
    }

    @Override
    public void matchWithDriver(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride=modelMapper.map(rideRequest,Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOtp());
        ride.setId(null);
        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
         return rideRepository.save(ride);

    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long rideId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }

    private String generateRandomOtp(){
        Random random=new Random();
        int otp=random.nextInt(10000);
        return String.format("%04d",otp);
    }
}
