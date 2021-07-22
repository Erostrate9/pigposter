package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
import java.util.*;
public interface BindRepository extends JpaRepository<Bind,Integer>{
    List<Bind> findBindsByUser(User user);
}
