package com.ly.oa.user.server.service;

import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.orika.mapper.UserDtoToUserDoMapper;

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
	 * 根据登录名获取用户
	 * @param loginName 登录名
	 * @return 用户信息
	 */
	UserDTO getUserByLoginName(String loginName);

	/**
	 * 保存用户信息
	 * @param userDTO 用户传输对象
	 * @return 保存后的用户对象
	 */
	UserDTO saveUser(UserDTO userDTO);

	/**
	 * 更新用户信息
	 * @param userDTO 用户信息
	 * @return 更新后的用户信息
	 */
	UserDTO updateUser(UserDTO userDTO);

	/**
	 * 删除用户
	 * @param userId 用户id
	 * @return 删除的用户信息
	 */
	UserDTO deleteUser(Long userId);

	/**
	 * 禁用用户
	 * @param userId 用户id
	 * @return 禁用掉的用户
	 */
	UserDTO forbiddenUser(Long userId);

	/**
	 * 启用用户
	 * @param userId 用户id
	 * @return 启用的用户信息
	 */
	UserDTO enableUser(Long userId);

	/**
	 * 情况用户缓存
	 */
	void clearCache();
}
