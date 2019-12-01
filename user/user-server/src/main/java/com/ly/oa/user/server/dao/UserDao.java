package com.ly.oa.user.server.dao;

import com.ly.oa.user.server.entity.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, String> {

}
