package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.repositories.DriverRepository;
import in.yash.UberApplication.services.UpdateDriverAvailability;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateDriverAvailabilityImpl implements UpdateDriverAvailability {

    private final DriverRepository driverRepository;

    @Override
    public void updateDriverAvailability(Driver driver, Boolean status) {
        driver.setAvailable(true);
        driverRepository.save(driver);
    }
}
