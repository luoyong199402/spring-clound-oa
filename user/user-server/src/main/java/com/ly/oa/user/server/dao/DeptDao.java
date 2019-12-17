package com.ly.oa.user.server.dao;

import com.ly.oa.user.server.entity.dos.DeptDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ly
 */
@Repository
public interface DeptDao extends JpaRepository<DeptDO, Long> {
	List<DeptDO> getAllByName(String name);
	Optional<DeptDO> getByCode(String code);

	Optional<DeptDO> deleteByCode(String code);
}
