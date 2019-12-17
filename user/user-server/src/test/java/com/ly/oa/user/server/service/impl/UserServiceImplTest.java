package com.ly.oa.user.server.service.impl;

import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.service.UserService;
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
class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Test
	void getUserById() {
		UserDTO userDto = userService.getUserById(1L);
		log.info("userDto = {}", userDto);
		Assert.notNull(userDto.getId(), "查询失败！");
	}

	@Test
	void saveUser() {
	}
}