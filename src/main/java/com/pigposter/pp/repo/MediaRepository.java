package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
public interface MediaRepository extends JpaRepository<Media,Integer>{
    Media findMediasByUser(User user);
    Media findMediasByPoster(Poster poster);
}
