package com.launchtrip.launchtrip.models.data;

import com.launchtrip.launchtrip.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
