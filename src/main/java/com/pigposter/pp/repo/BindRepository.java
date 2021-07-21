package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
public interface BindRepository extends JpaRepository<Bind,Integer>{
    Bind findBindsByUser(User user);
}
