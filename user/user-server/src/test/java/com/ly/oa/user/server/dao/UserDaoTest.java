package com.ly.oa.user.server.dao;

import com.ly.oa.user.server.constant.enums.SexEnum;
import com.ly.oa.user.server.entity.dos.DeptDO;
import com.ly.oa.user.server.entity.dos.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
/*@Transactional
@Rollback*/
public class UserDaoTest {
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
		Assert.assertNotNull(user.getId());
	}

	@Test
	public void find() {
		save();
		Optional<UserDO> userEntity = userDao.getByLoginName("ly");
		Assert.assertNotNull(userEntity.get().getId());
	}

	@Test
	public void update() {
		save();
		Optional<UserDO> user = userDao.getByLoginName("ly");
		user.get().setEmail("123@qq.com");
		UserDO saveUser = userDao.save(user.get());
		Assert.assertEquals(saveUser.getEmail(), "123@qq.com");
	}

	@Test
	public void delete() {
		save();
		UserDO userEntity = UserDO.builder().loginName("ly").build();
		Optional<UserDO> userDO = userDao.removeByLoginName(userEntity.getLoginName());
		log.info("delete user = {}", userDO.get());

		Optional<UserDO> queryUser = userDao.getByLoginName(userEntity.getLoginName());
		Assert.assertEquals(queryUser, Optional.empty());
	}

	@Test
	public void cascadeSave() {
		UserDO user = getUser();
		List<DeptDO> detpList = getDetpList();
		user.setDeptList(detpList);
		UserDO saveUser = userDao.save(user);

		Assert.assertEquals(saveUser.getDeptList().size(), detpList.size());
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

		userDao.save(ly2.get());
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