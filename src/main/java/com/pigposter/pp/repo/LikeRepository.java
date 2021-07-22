package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
import java.util.*;
public interface LikeRepository extends JpaRepository<Like,Integer>{
    List<Like> findLikesByUserAndPid(User user,Poster pid);
    List<Like> findLikesByPid(Poster poster);
    List<Like> findLikesByUser(User user);
}
