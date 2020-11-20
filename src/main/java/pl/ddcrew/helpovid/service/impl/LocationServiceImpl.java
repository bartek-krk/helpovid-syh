package pl.ddcrew.helpovid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ddcrew.helpovid.model.Location;
import pl.ddcrew.helpovid.repository.LocationRepository;
import pl.ddcrew.helpovid.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    @Override
    public void saveLocation(Location location) {
        locationRepository.save(location);
    }
}
