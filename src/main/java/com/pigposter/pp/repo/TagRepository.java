package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
import java.util.*;
public interface TagRepository extends JpaRepository<Tag,Integer>{
    List<Tag> findTagsByText(String text);
    List<Tag> findTagsByUser(User user);
    List<Tag> findTagsByPid(Poster poster);
}
