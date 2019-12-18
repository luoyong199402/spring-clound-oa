package com.ly.oa.user.server.service.impl;

import com.ly.oa.common.orika.OrikaBeanMapper;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.constant.enums.SexEnum;
import com.ly.oa.user.server.entity.dos.UserDO;
import com.ly.oa.user.server.orika.mapper.UserDtoToUserDoMapper;
import com.ly.oa.user.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Autowired
	private OrikaBeanMapper orikaBeanMapper;

	@Test
	void getUserById() {
		UserDTO userDTO = getUserDTO();
		userDTO = userService.saveUser(userDTO);

		UserDTO userDto = userService.getUserById(userDTO.getId());
		log.info("userDto = {}", userDto);
		Assert.notNull(userDto.getId(), "查询失败！");
	}

	@Test
	void saveUser() {
		UserDTO userDTO = getUserDTO();

		userDTO = userService.saveUser(userDTO);
		log.info("user = {}", userDTO);
		Assert.notNull(userDTO.getId(), "报错用户失败！");

	}

	private UserDTO getUserDTO() {
		return UserDTO.builder()
				.id(1L)
				.firstName("luo")
				.lastName("yong")
				.loginName("luoyong")
				.password("123")
				.isEnable(true)
				.sex(SexEnum.MAIL.getValue())
				.email("1@1.com").build();
	}

	@Test
	void forbiddenUser() {
		UserDTO userDTO = getUserDTO();
		userDTO = userService.saveUser(userDTO);

		UserDTO updateUser = userService.forbiddenUser(userDTO.getId());
		log.info("updateUser = {}", updateUser);
		Assert.notNull(updateUser.getId(), "更新失败！");
	}

	@Test
	void enableUser() {
		UserDTO userDTO = getUserDTO();
		userDTO = userService.saveUser(userDTO);

		UserDTO updateUser = userService.enableUser(userDTO.getId());
		log.info("updateUser = {}", updateUser);
		Assert.notNull(updateUser.getId(), "更新失败！");
	}

	@Test
	void getUserByLoginName() {
		UserDTO userDTO = getUserDTO();
		userDTO = userService.saveUser(userDTO);

		UserDTO loginUser = userService.getUserByLoginName(userDTO.getLoginName());
		Assert.notNull(loginUser.getId(), "获取用户失败！");
	}

	@Test
	void clearCache() {
		userService.clearCache();
	}

	@Test
	void deleteUser() {
		UserDTO userDTO = getUserDTO();
		userDTO = userService.saveUser(userDTO);

		UserDTO deleteUser = userService.deleteUser(userDTO.getId());
		Assert.notNull(deleteUser, "删除用户失败！");
	}

	@Test
	void updateUser() {
		UserDTO userDTO = getUserDTO();
		userDTO = userService.saveUser(userDTO);

		userDTO.setEmail("haha@keke.com");
		UserDTO updateUser = userService.updateUser(userDTO);
		Assert.isTrue(StringUtils.equals(updateUser.getEmail(), userDTO.getEmail()), "更新失败！");
	}
}