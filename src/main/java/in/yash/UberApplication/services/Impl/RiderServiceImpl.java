package in.yash.UberApplication.services.Impl;


import in.yash.UberApplication.dto.RatingDto;
import in.yash.UberApplication.dto.RideDto;
import in.yash.UberApplication.dto.RideRequestDto;
import in.yash.UberApplication.dto.RiderDto;
import in.yash.UberApplication.entities.*;
import in.yash.UberApplication.entities.enums.RideRequestStatus;
import in.yash.UberApplication.entities.enums.RideStatus;
import in.yash.UberApplication.exceptions.ResourceNotFoundException;
import in.yash.UberApplication.exceptions.RideException;
import in.yash.UberApplication.repositories.RideRepository;
import in.yash.UberApplication.repositories.RideRequestRepository;
import in.yash.UberApplication.repositories.RiderRepository;
import in.yash.UberApplication.services.*;
import in.yash.UberApplication.strategies.RideStrategyManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RideStrategyManager rideStrategyManager;

    @Autowired
    private RideRequestRepository rideRequestRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private RideService rideService;

    @Autowired
    private UpdateDriverAvailability updateDriverAvailability;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RideRequestService rideRequestService;


    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        double fare = rideStrategyManager.rideFairCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> nearByDrivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);
        //TODO send notification to all the drivers about this ride request
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    @Transactional
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride=rideService.getRideById(rideId);
        if (!ride.equals(ride.getRider())) {
            throw new RideException("Ride does not own this ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RideException("Ride cannot be cancelled");
        }
        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailability.updateDriverAvailability(ride.getDriver(),true);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    @Transactional
    public RatingDto rateDriver(Long rideId, Integer rating) {
        Ride savedRide = rideRepository.findById(rideId).orElseThrow(()->new ResourceNotFoundException("Ride not found"));
        Double totalRating = ratingService.rateDriver(savedRide, rating);
        String message="Total rating of rider is :"+totalRating;
        return RatingDto.builder().message(message).build();
    }


    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider, RiderDto.class);
    }

    @Override
    public Page<RideDto> getMyAllRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider, pageRequest).map(
                ride -> modelMapper.map(ride, RideDto.class)
        );

    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider =new Rider();
        rider.setUser(user);
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        User  user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Rider rider = riderRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Driver not associated with user with id " +user.getId() ));
        System.out.println(rider);
        return rider;
    }

}
