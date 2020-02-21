package com.ly.oa.authority.server.dao;

import com.ly.oa.authority.server.entity.dos.GroupDO;
import com.ly.oa.authority.server.entity.dos.MenuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends JpaRepository<MenuDO, Long> {
}
