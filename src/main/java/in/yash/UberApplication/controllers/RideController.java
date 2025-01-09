package in.yash.UberApplication.controllers;

import in.yash.UberApplication.dto.DriverDto;
import in.yash.UberApplication.dto.RideDto;
import in.yash.UberApplication.dto.RideRequestDto;
import in.yash.UberApplication.dto.RiderDto;
import in.yash.UberApplication.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
public class RideController {

    @Autowired
    private RiderService riderService;

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto>cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver")
    public ResponseEntity<DriverDto>rateDriver(Long rideId,Integer rating){
        return ResponseEntity.ok(riderService.rateDriver(rideId,rating));
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<RiderDto>getMyProfile(){
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping("/getAllRides")
    public ResponseEntity<Page<RideDto>>getAllRides(@RequestParam(defaultValue = "0")Integer page,
                                                    @RequestParam(defaultValue = "10")Integer pageSize){
        Page<RideDto>rides=riderService.getMyAllRides(PageRequest.of(page,pageSize));
        return ResponseEntity.ok (rides);
    }

}
