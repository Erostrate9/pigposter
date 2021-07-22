package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
import java.util.*;
public interface FollowRepository extends JpaRepository<Follow,Integer>{
    List<Follow> findFollowsByFollowee(User user);
    List<Follow> findFollowsByFollower(User user);
}
