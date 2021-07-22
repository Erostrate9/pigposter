package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
import java.util.*;
public interface WorkSheetRepository extends JpaRepository<WorkSheet,String>{
    List<WorkSheet> findWorkSheetsByUser(User user);
    List<WorkSheet> findWorkSheetsByResult(int result);
}
