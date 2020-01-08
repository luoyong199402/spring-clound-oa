package com.ly.oa.user.server.dao;

import com.ly.oa.user.server.entity.dos.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author ly
 */
@Repository
public interface UserDao extends JpaRepository<UserDO, Long>, JpaSpecificationExecutor<UserDO> {
	Optional<UserDO> getByLoginName(String loginName);
	Optional<UserDO> removeByLoginName(String loginName);
}

