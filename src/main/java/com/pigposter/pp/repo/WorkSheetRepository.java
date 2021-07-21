package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.*;
public interface WorkSheetRepository extends JpaRepository<WorkSheet,String>{
    WorkSheet findWorkSheetsByUser(User user);
    WorkSheet findWorkSheetsByResult(int result);
}
