package com.chaitu.fixmyride.service;

import com.chaitu.fixmyride.model.MechanicLocation;
import com.chaitu.fixmyride.repo.MechanicLocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
public class MechanicLocationService {

    @Autowired
    private MechanicLocationRepo repo;

    // Get all mechanic locations
    public List<MechanicLocation> getAllLocations() {
        return repo.findAll();
    }

    // Get mechanic location by ID
    public MechanicLocation getLocationById(int id) {
        return repo.findById(id).orElse(null);
    }

    // Create new mechanic location
    public MechanicLocation addLocation(MechanicLocation location) {
        location.setLast_updated(new Date());
        return repo.save(location);
    }

    // Update mechanic location (PUT)
    public MechanicLocation updateLocation(int id, MechanicLocation location) {

        MechanicLocation existing = repo.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        // Update only allowed fields
        if (location.getCurrent_lat() != 0) {
            existing.setCurrent_lat(location.getCurrent_lat());
        }

        if (location.getCurrent_long() != 0) {
            existing.setCurrent_long(location.getCurrent_long());
        }

        existing.setLast_updated(new Date());

        return repo.save(existing);
    }
    public MechanicLocation getByMechanicId(int mechanicId) {
        return repo.findByMechanicId(mechanicId);
    }
    public MechanicLocation updateByMechanicId(int mechanicId, MechanicLocation newLocation) {

        MechanicLocation existing = repo.findByMechanicId(mechanicId);

        if (existing == null) return null;

        existing.setCurrent_lat(newLocation.getCurrent_lat());
        existing.setCurrent_long(newLocation.getCurrent_long());
        existing.setLast_updated(new Date());

        return repo.save(existing);
    }


    // Delete mechanic location
    public void deleteLocationById(int id) {
        repo.deleteById(id);
    }
}
