package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
import java.util.*;
public interface CommentRepository extends JpaRepository<Comment,Integer>{
    List<Comment> findCommentsByUser(User user);
    List<Comment> findCommentsByPid(Poster poster);
    Comment findCommentById(int id);
}
