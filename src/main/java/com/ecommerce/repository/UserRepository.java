package com.ecommerce.repository;

import com.ecommerce.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInformation,Integer> {



    Optional<UserInformation> findByUserName(String username);
}
