package in.yash.UberApplication.controllers;

import in.yash.UberApplication.dto.DriverDto;
import in.yash.UberApplication.dto.RideDto;
import in.yash.UberApplication.dto.RideStartDto;
import in.yash.UberApplication.dto.RiderDto;
import in.yash.UberApplication.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId, @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDto.getOtp()));
    }

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<RideDto> endRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

    @PostMapping("rateRider/{rideId}/{rating}")
    public ResponseEntity<RiderDto>rateRider(@PathVariable Long rideId, @PathVariable double rating){
        return ResponseEntity.ok(driverService.rateRider(rideId,rating));
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<DriverDto>getMyProfile(){
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @GetMapping("/getAllRides")
    public ResponseEntity<Page<RideDto>>getAllRides(@RequestParam(defaultValue = "0")Integer page,
                                                    @RequestParam(defaultValue = "10")Integer size){
        return ResponseEntity.ok(driverService.getMyAllRides(PageRequest.of(page,size)));
    }


}
