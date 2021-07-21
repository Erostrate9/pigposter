package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
public interface PosterRepository extends JpaRepository<Poster,Integer>{
    Poster findPostersByUser(User user);
    
}
