package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
public interface TagRepository extends JpaRepository<Tag,Integer>{
    Tag findTagsByText(String text);
    Tag findTagsByUser(User user);
    Tag findTagsByPoster(Poster poster);
}
