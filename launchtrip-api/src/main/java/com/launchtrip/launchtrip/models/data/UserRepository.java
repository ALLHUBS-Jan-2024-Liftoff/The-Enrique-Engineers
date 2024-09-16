package com.launchtrip.launchtrip.models.data;

import com.launchtrip.launchtrip.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);}




