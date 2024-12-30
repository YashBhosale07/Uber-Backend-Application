package in.yash.UberApplication.strategies;

import in.yash.UberApplication.strategies.Impl.DriverMatchingHighestRatedDriverStrategy;
import in.yash.UberApplication.strategies.Impl.DriverMatchingNearestDriverStrategy;
import in.yash.UberApplication.strategies.Impl.RideFairDefaultFareCalculationStrategy;
import in.yash.UberApplication.strategies.Impl.RideFairSurgePricingFareCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class RideStrategyManager {

    @Autowired
    private DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;

    @Autowired
    private DriverMatchingNearestDriverStrategy nearestDriverStrategy;

    @Autowired
    private RideFairDefaultFareCalculationStrategy defaultFareCalculationStrategy;

    @Autowired
    private RideFairSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;

   public DriverMatchingStrategy driverMatchingStrategy(double rideRating){
        if(rideRating>=4.8){
            return highestRatedDriverStrategy;
        }else{
            return nearestDriverStrategy;
        }
   }

   public RideFairCalculationStrategy rideFairCalculationStrategy(){
       //6pm to 9pm
       LocalTime surgeTime=LocalTime.of(18,00);
       LocalTime endTime=LocalTime.of(21,00);
       LocalTime currentTime=LocalTime.now();
        if(currentTime.isAfter(surgeTime) && currentTime.isBefore(endTime)){
            return surgePricingFareCalculationStrategy;
        }else{
            return defaultFareCalculationStrategy;
        }
   }

}
