package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
public interface FollowRepository extends JpaRepository<Follow,Integer>{
    Follow findFollowsByFollowee(User user);
    Follow findFollowsByFollower(User user);
}
