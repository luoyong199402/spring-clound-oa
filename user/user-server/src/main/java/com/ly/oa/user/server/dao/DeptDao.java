package com.ly.oa.user.server.dao;

import com.ly.oa.user.server.entity.dos.DeptDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ly
 */
@Repository
public interface DeptDao extends JpaRepository<DeptDO, Long> {
}
