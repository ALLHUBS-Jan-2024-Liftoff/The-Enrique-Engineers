package com.launchtrip.launchtrip.models.data;

import com.launchtrip.launchtrip.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByCity(String city);

    //List<Location> findByCategories(String categories);
}
