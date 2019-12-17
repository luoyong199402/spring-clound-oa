package com.ly.oa.user.server.service;

import com.ly.oa.user.server.api.dto.UserDTO;

/**
 * @author ly
 */
public interface UserService {
	/**
	 * 通过用户id获取用户信息
	 * @param userId 用户id
	 * @return 用户信息
	 */
	UserDTO getUserById(Long userId);

	/**
	 * 保存用户信息
	 * @param userDTO 用户传输对象
	 * @return 保存后的用户对象
	 */
	UserDTO saveUser(UserDTO userDTO);
}
