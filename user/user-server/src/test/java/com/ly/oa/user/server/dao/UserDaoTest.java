package com.ly.oa.user.server.dao;

import com.ly.oa.user.server.constant.enums.SexEnum;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
@Transactional
@Rollback
class UserDaoTest {
	@Autowired
	UserDao userDao;

	@Autowired
	DeptDao deptDao;

	@Test
	public void save() {
		UserDO user = UserDO.builder()
				.firstName("luo")
				.lastName("yong")
				.password("123")
				.loginName("ly")
				.sex(SexEnum.MAIL).build();
		user = userDao.save(user);

		log.info("user = {}", user);
		Assert.notNull(user.getId(), "保存失败！");
	}

	@Test
	public void find() {
		save();
		Optional<UserDO> userEntity = userDao.getByLoginName("ly");
		Assert.notNull(userEntity.get().getId(), "查询失败！");
	}

	@Test
	public void update() {
		save();
		Optional<UserDO> user = userDao.getByLoginName("ly");
		user.get().setEmail("123@qq.com");
		UserDO saveUser = userDao.save(user.get());
		Assert.isTrue(StringUtils.equals(saveUser.getEmail(), "123@qq.com"), "更新失败！");
	}

	@Test
	public void delete() {
		save();
		UserDO userEntity = UserDO.builder().loginName("ly").build();
		Optional<UserDO> userDO = userDao.removeByLoginName(userEntity.getLoginName());
		log.info("delete user = {}", userDO.get());

		Optional<UserDO> queryUser = userDao.getByLoginName(userEntity.getLoginName());
		Assert.isTrue(queryUser.equals(Optional.empty()), "删除失败！");
	}

	@Test
	public void cascadeSave() {
		UserDO user = getUser();
		List<DeptDO> deptList = getDetpList();
		user.setDeptList(deptList);

		deptDao.saveAll(deptList);
		UserDO saveUser = userDao.save(user);
		log.info("{}", saveUser);

		Assert.notNull(saveUser.getId(), "级联保存失败！");
	}

	@Test
	public void cascadeUpdate() {
		cascadeSave();

		Optional<UserDO> ly2 = userDao.getByLoginName("ly2");
		List<DeptDO> deptSet = ly2.get().getDeptList();
		List<DeptDO> newName = deptSet.stream().map(deptDO -> {
			deptDO.setName("newName");
			return deptDO;
		}).limit(1).collect(Collectors.toList());

		ly2.get().setDeptList(newName);

		UserDO userDO = userDao.save(ly2.get());
		Assert.notNull(userDO, "级联更新失败！");
	}

	@Test
	public void cascadeDelete() {
		cascadeSave();

		Optional<UserDO> ly2 = userDao.getByLoginName("ly2");
		List<DeptDO> deptSet = ly2.get().getDeptList();
		deptSet.clear();

		userDao.save(ly2.get());
	}

	@Test
	public void cascadeDelete2() {
		cascadeSave();

		Optional<UserDO> ly2 = userDao.getByLoginName("ly2");

		userDao.delete(ly2.get());
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

	private List<DeptDO> getDetpList() {
		List<DeptDO> deptDOList = Stream.iterate(1, x -> x + 1)
				.map(val -> {
					return DeptDO.builder()
							.name("测试部门")
							.shortName("简称")
							.isEnable(true)
							.code(String.valueOf(val))
							.sort(String.valueOf(val))
							.tel("123")
							.parentId(String.valueOf(val))
							.address("123").build();

				})
				.limit(10).collect(Collectors.toList());
		return deptDOList;
	}
}