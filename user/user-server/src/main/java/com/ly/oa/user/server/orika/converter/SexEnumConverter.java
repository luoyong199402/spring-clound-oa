package com.ly.oa.user.server.orika.converter;

import com.ly.oa.user.server.constant.enums.SexEnum;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

/**
 * 性别枚举和字符串的相互转换。
 * @author ly
 */
@Component
public class SexEnumConverter extends BidirectionalConverter<SexEnum, String> {

	@Override
	public String convertTo(SexEnum source, Type<String> destinationType, MappingContext mappingContext) {
		return source.getValue();
	}

	@Override
	public SexEnum convertFrom(String source, Type<SexEnum> destinationType, MappingContext mappingContext) {
		return SexEnum.fromCode(source);
	}
}
