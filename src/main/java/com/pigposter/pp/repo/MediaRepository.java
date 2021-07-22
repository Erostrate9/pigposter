package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
import java.util.*;
public interface MediaRepository extends JpaRepository<Media,Integer>{
    
    List<Media> findMediasByPid(Poster poster);
}
