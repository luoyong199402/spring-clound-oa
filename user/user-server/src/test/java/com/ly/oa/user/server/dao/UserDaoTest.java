package com.ly.oa.user.server.dao;

import com.ly.oa.user.server.constant.enums.SexEnum;
import com.ly.oa.user.server.entity.dao.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
@Transactional
@Rollback
public class UserDaoTest {
	@Autowired
	UserDao userDao;

	@Test
	public void save() {
		UserEntity user = UserEntity.builder()
				.id("123")
				.firstName("luo")
				.lastName("yong")
				.password("123")
				.loginName("ly")
				.sex(SexEnum.MAIL).build();
		user = userDao.save(user);

		Assert.assertNotNull(user.getId());
	}

	@Test
	public void findById() {
		save();
		Optional<UserEntity> userEntity = userDao.findById("123");
		Assert.assertNotNull(userEntity.get().getId());
	}

	@Test
	public void udpate() {
		save();
		Optional<UserEntity> user = userDao.findById("123");
		user.get().setEmail("123@qq.com");
		UserEntity saveUser = userDao.save(user.get());
		Assert.assertEquals(saveUser.getEmail(), "123@qq.com");
	}

	@Test
	public void delete() {
		save();
		UserEntity userEntity = UserEntity.builder().id("123").build();
		userDao.delete(userEntity);

		Optional<UserEntity> queryUser = userDao.findById(userEntity.getId());
		Assert.assertEquals(queryUser, Optional.empty());
	}

}