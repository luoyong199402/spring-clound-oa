package com.ly.oa.user.server.api.feign.fallback;

import com.ly.oa.common.exception.InternalApiException;
import com.ly.oa.common.page.Page;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import com.ly.oa.user.server.api.query.UserQuery;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author ly
 */
@Data
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

	private Throwable cause;

	@Override
	public UserDTO getUserById(Long id) throws InternalApiException {
		log.error("获取用户失败！ userId = {}", id);
		cause.getMessage();
		throw (InternalApiException)cause;
	}

	@Override
	public Page<UserDTO> queryUser(Long id, String firstName, String lastName, String loginName, Boolean isEnable, String email, Date createTimeStartTime, Date createTimeEndTime, Integer page, Integer integer, List<String> sort) {
		return null;
	}

	@Override
	public Page<UserDTO> queryUser(UserQuery userQuery) {
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
