package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.Avatar;
public interface AvatarRepository extends JpaRepository<Avatar,Integer>{
    Avatar findAvatarByPath(String path);
}
