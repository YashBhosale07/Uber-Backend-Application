package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.entities.RideRequest;
import in.yash.UberApplication.exceptions.ResourceNotFoundException;
import in.yash.UberApplication.repositories.RideRequestRepository;
import in.yash.UberApplication.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride Request not found with id " + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ride Request not found with id " + rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
