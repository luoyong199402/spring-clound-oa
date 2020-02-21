package com.ly.oa.authority.server.dao;

import com.ly.oa.authority.server.entity.dos.PageElementDO;
import com.ly.oa.authority.server.entity.dos.RoleUserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserDao extends JpaRepository<RoleUserDO, RoleUserDO> {
}
