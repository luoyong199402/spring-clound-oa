package com.ly.oa.user.server.orika.mapper;

import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.entity.dos.UserDO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * @author ly
 * 	存在一些问题。
 */
//@Component
public class UserDtoToUserDoMapper extends CustomMapper<UserDTO, UserDO> {

	@Override
	public void mapAtoB(UserDTO userDTO, UserDO userDO, MappingContext context) {
	}

	@Override
	public void mapBtoA(UserDO userDO, UserDTO userDTO, MappingContext context) {
	}
}
