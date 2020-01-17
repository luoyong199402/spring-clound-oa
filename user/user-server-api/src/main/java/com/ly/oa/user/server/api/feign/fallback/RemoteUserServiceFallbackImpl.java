package com.ly.oa.user.server.api.feign.fallback;

import com.ly.oa.common.exception.InternalApiException;
import com.ly.oa.common.page.Page;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import com.ly.oa.user.server.api.query.UserQuery;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * @author ly
 */
@Data
@Slf4j
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

	private Throwable cause;

	@Override
	public UserDTO getUserById(Long id) throws InternalApiException {
		log.warn("获取用户失败！ userId = {}", id);
		return null;
	}

	@Override
	public Page<UserDTO> queryUser(Long id, String firstName, String lastName, String loginName, Boolean isEnable, String email, Date createTimeStartTime, Date createTimeEndTime, Integer page, Integer integer, List<String> sort) {
		log.warn("查询用户失败！ ");
		return null;
	}

	@Override
	public Page<UserDTO> queryUser(UserQuery userQuery) {
		log.warn("查询用户失败! {}", userQuery);
		return null;
	}


	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		log.warn("保存用户失败! {}", userDTO);
		return null;
	}

	@Override
	public UserDTO getUserByLoginName(String loginName) {
		log.warn("查询用户失败! {}", loginName);
		return null;
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		log.warn("更新用户失败! id = {}，info = {}", id, userDTO);
		return null;
	}

	@Override
	public UserDTO deleteUser(Long id) {
		log.warn("删除用户失败! id = {}", id);
		return null;
	}

	@Override
	public UserDTO forbiddenUser(Long id) {
		log.warn("禁用用户失败! id = {}", id);
		return null;
	}

	@Override
	public UserDTO enableUser(Long id) {
		log.warn("启用用户失败! id = {}", id);
		return null;
	}
}
