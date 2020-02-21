package com.ly.oa.authority.server.dao;

import com.ly.oa.authority.server.entity.dos.AuthorityDO;
import com.ly.oa.authority.server.entity.dos.FunctionalOperationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalOperationDao extends JpaRepository<FunctionalOperationDO, Long> {
}
