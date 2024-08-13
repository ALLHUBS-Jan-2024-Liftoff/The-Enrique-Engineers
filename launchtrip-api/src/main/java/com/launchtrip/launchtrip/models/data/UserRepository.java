package com.launchtrip.launchtrip.models.data;

import com.launchtrip.launchtrip.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}




