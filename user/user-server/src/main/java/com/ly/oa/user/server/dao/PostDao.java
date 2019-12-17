package com.ly.oa.user.server.dao;

import com.ly.oa.user.server.entity.dos.PostDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ly
 */
@Repository
public interface PostDao extends JpaRepository<PostDO, Long> {
	Optional<PostDO> getByCode(String code);

	Optional<PostDO> deleteByCode(String code);
}
