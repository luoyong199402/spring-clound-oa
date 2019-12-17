package com.ly.oa.user.server.orika.mapper;

import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.constant.enums.SexEnum;
import com.ly.oa.user.server.entity.dos.UserDO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author ly
 */
@Component
public class UserDtoToUserDoMapper extends CustomMapper<UserDTO, UserDO> {
	@Override
	public void mapAtoB(UserDTO userDTO, UserDO userDO, MappingContext context) {
		userDO.setSex(SexEnum.fromCode(userDTO.getSex()));
	}

	@Override
	public void mapBtoA(UserDO userDO, UserDTO userDTO, MappingContext context) {
		super.mapBtoA(userDO, userDTO, context);
		userDTO.setSex(userDO.getSex().getValue());
		userDTO.setPassword("");
	}
}
