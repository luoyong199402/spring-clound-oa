package com.ly.oa.user.server.service.impl;

import com.ly.oa.common.orika.OrikaBeanMapper;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.dao.UserDao;
import com.ly.oa.user.server.entity.dos.UserDO;
import com.ly.oa.user.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ly
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrikaBeanMapper orikaBeanMapper;

	@Override
	public UserDTO getUserById(Long userId) {
		Optional<UserDO> userDoOptional = userDao.findById(userId);
		return convertByUserDO(userDoOptional.get());
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		return null;
	}

	private UserDTO convertByUserDO(UserDO userDO) {
		return orikaBeanMapper.map(userDO, UserDTO.class);
	}

	private UserDO convertToUserDO(UserDTO userDTO) {
		return orikaBeanMapper.map(userDTO, UserDO.class);
	}
}
