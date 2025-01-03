package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.dto.DriverDto;
import in.yash.UberApplication.dto.RideDto;
import in.yash.UberApplication.dto.RiderDto;
import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.RideRequest;
import in.yash.UberApplication.entities.enums.RideRequestStatus;
import in.yash.UberApplication.entities.enums.RideStatus;
import in.yash.UberApplication.exceptions.*;
import in.yash.UberApplication.repositories.DriverRepository;
import in.yash.UberApplication.services.DriverService;
import in.yash.UberApplication.services.RideRequestService;
import in.yash.UberApplication.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;

    private final DriverRepository driverRepository;

    private final RideService rideService;

    private final ModelMapper modelMapper;
    @Override
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest=rideRequestService.findRideRequestById(rideRequestId);
        if(rideRequest==null){
            throw new ResourceNotFoundException("Error");
        }
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RideRequestNotAcceptedException("Ride request cannot be accepted, status is "+rideRequest.getRideRequestStatus());
        }
        Driver currentDriver=getCurrentDriver();
        if(!currentDriver.getAvailable()){
            throw new DriverNotAvailableForRideException("Driver cannot accept ride due to unavailability");
        }
        currentDriver.setAvailable(false);
        Driver savedDriver=driverRepository.save(currentDriver);

        Ride ride=rideService.createNewRide(rideRequest,savedDriver);
        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride=rideService.getRideById(rideId);
        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has accepted the ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RideException("Ride not be cancelled");
        }
        rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        driver.setAvailable(true);
        driverRepository.save(driver);
        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto startRide(Long rideId,String otp) {

        Ride ride=rideService.getRideById(rideId);
        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has accepted the ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride status is not confirmed hence cannot be started, status "+ride.getRideStatus());
        }

        if(!otp.equals(ride.getOtp())){
            throw new OtpInvalidException("Otp is invalid");
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide=rideService.updateRideStatus(ride,RideStatus.ONGOING);

        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        Driver driver=getCurrentDriver();
        return modelMapper.map(driver,DriverDto.class);
    }

    @Override
    public Page<RideDto> getMyAllRides(PageRequest pageRequest) {
        Driver driver=getCurrentDriver();
        return rideService.getAllRidesOfDriver(driver.getId(), pageRequest).map(ride ->
            modelMapper.map(ride,RideDto.class)
        );
    }

    @Override
    public void updateDriverAvailability(Driver driver, boolean available) {
       driver.setAvailable(true);
       driverRepository.save(driver);
    }


    public Driver getCurrentDriver(){
        return driverRepository.findById(2l)
                .orElseThrow(()->new ResourceNotFoundException("Driver not found with id "+2));
    }
}
