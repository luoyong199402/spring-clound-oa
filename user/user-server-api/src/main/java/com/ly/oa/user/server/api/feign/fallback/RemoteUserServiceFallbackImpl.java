package com.ly.oa.user.server.api.feign.fallback;

import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ly
 */
@Data
@Slf4j
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

	private Throwable cause;

	@Override
	public UserDTO getUserById(Long id) {
		log.error("获取用户失败！ userId = {}", id);
		return null;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		log.info("保存用户失败！ userDTO = {}", userDTO);
		return null;
	}

	@Override
	public UserDTO getUserByLoginName(String loginName) {
		return null;
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		return null;
	}

	@Override
	public UserDTO deleteUser(Long id) {
		return null;
	}

	@Override
	public UserDTO forbiddenUser(Long id) {
		return null;
	}

	@Override
	public UserDTO enableUser(Long id) {
		return null;
	}
}
