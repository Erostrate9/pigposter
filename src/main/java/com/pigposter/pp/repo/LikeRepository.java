package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
public interface LikeRepository extends JpaRepository<Like,Integer>{
    Like findLikesByUser(User user);
    Like findLikesByPid(Poster poster);
}
