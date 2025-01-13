package in.yash.UberApplication.services;

import in.yash.UberApplication.entities.Driver;

public interface UpdateDriverAvailability {
    void updateDriverAvailability(Driver driver, Boolean status);
}
