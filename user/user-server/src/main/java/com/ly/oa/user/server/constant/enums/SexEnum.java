package com.ly.oa.user.server.constant.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 性别枚举
 * @author ly
 */
@Getter
public enum SexEnum {
	/**
	 * 男性
	 */
	MAIL("1", "男性"),

	/**
	 * 女性
	 */
	FEMAIL("2", "女性");

	private String value;
	private String description;

	SexEnum(String value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * 通过code转换为枚举
	 * @param value 编码
	 * @return 枚举信息
	 */
	public static SexEnum fromCode(String value) {
		List<SexEnum> sexEnumList = Arrays.stream(SexEnum.values())
				.filter(sexEnum -> StringUtils.equals(value, sexEnum.value))
				.collect(Collectors.toList());

		if (sexEnumList != null && sexEnumList.size() > 0) {
			return sexEnumList.get(0);
		}
		return null;
	}

	@Converter(autoApply = true)
	public static class SexEnumConverter implements AttributeConverter<SexEnum, String> {
		@Override
		public String convertToDatabaseColumn(SexEnum attribute) {
			return attribute.getValue();
		}

		@Override
		public SexEnum convertToEntityAttribute(String dbData) {
			return SexEnum.fromCode(dbData);
		}
	}
}
