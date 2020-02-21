package com.ly.oa.authority.server.dao;

import com.ly.oa.authority.server.entity.dos.GroupDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback
class GroupDaoTest {

    @Autowired
    private GroupDao groupDao;

    @Test
    public void save() {
        GroupDO groupDO = GroupDO.builder()
                .name("测试")
                .code("123")
                .parentGroupId(null)
                .build();

        groupDO = groupDao.save(groupDO);

        log.info("groupDO = {}", groupDO);
        Assert.notNull(groupDO.getId(), "保存失败！");
    }

}