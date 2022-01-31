package com.learnner.jwtsecurityservice.repository;

import com.learnner.jwtsecurityservice.entityse.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String userName);
}
