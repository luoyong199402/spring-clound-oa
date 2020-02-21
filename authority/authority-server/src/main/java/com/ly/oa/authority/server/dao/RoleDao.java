package com.ly.oa.authority.server.dao;

import com.ly.oa.authority.server.entity.dos.MenuDO;
import com.ly.oa.authority.server.entity.dos.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<RoleDO, Long> {
}
