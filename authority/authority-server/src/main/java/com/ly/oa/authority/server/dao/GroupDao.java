package com.ly.oa.authority.server.dao;

import com.ly.oa.authority.server.entity.dos.FileDO;
import com.ly.oa.authority.server.entity.dos.GroupDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends JpaRepository<GroupDO, Long> {
}
