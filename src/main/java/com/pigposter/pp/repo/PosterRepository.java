package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
import java.util.*;
public interface PosterRepository extends JpaRepository<Poster,Integer>{
    List<Poster> findPostersByUser(User user);
    Poster findPosterById(int id);
}
