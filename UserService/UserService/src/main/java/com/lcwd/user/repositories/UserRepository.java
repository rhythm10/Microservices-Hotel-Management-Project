package com.lcwd.user.repositories;

import com.lcwd.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    // Implement any custom Method or Query
}
