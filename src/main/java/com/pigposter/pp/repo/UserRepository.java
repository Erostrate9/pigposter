package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.User;
public interface UserRepository extends JpaRepository<User,String>{
    User findUserByUsername(String username);
}
