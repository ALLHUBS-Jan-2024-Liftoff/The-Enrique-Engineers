package com.launchtrip.launchtrip.models.data;

import com.launchtrip.launchtrip.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByCity(String city);
}
