package com.ly.oa.user.server.dao;

import com.google.common.collect.Lists;
import com.ly.oa.user.server.api.enums.SexEnum;
import com.ly.oa.user.server.entity.dos.DeptDO;
import com.ly.oa.user.server.entity.dos.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback
class DeptDaoTest {
	@Autowired
	private DeptDao deptDao;

	@Autowired
	private UserDao userDao;

	@Test
	public void save() {
		DeptDO deptDO = getDept();
		deptDO = deptDao.save(deptDO);
		Assert.notNull(deptDO.getId(), "保存失败！");

	}

	@Test
	public void delete() {
		save();
		deptDao.deleteByCode("1");
		Optional<DeptDO> optional = deptDao.getByCode("1");
		Assert.isTrue(optional.equals(Optional.empty()), "查询结果为空！");

	}

	@Test
	public void modify() {
		save();
		Optional<DeptDO> optional = deptDao.getByCode("1");
		optional.get().setName("new_name");
		DeptDO saveDeptDo = deptDao.save(optional.get());

		Assert.isTrue(StringUtils.equals(saveDeptDo.getName(), "new_name"), "修改操作失败！");

	}

	@Test
	public void find() {
		save();
		Optional<DeptDO> byCode = deptDao.getByCode("1");
		Assert.notNull(byCode.get(), "查询失败！");
	}

	@Test
	public void findDeptOfUser() {
		UserDO user = getUser();

		DeptDO dept = getDept();
		List<DeptDO> deptList = Lists.newArrayList(dept);
		user.setDeptList(deptList);

		deptDao.save(dept);
		userDao.save(user);

		Optional<DeptDO> byCode = deptDao.getByCode(dept.getCode());
		log.info("deptDo = {}", byCode.get());
		Assert.notNull(byCode.get().getId(), "级联查询失败");

	}

	private DeptDO getDept() {
		return DeptDO.builder()
				.name("测试部门")
				.shortName("简称")
				.sort("1")
				.code("1")
				.address("地址")
				.tel("123010101")
				.isEnable(true).build();
	}

	private UserDO getUser() {
		UserDO user = UserDO.builder()
				.firstName("luo")
				.lastName("yong")
				.password("123")
				.loginName("ly2")
				.sex(SexEnum.MAIL).build();
		return user;
	}

}