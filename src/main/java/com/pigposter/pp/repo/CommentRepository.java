package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
public interface CommentRepository extends JpaRepository<Comment,Integer>{
    Comment findCommentsByUser(User user);
    Comment findCommentsByPoster(Poster poster);
}
