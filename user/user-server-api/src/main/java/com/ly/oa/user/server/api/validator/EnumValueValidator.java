package com.ly.oa.user.server.api.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对枚举类型进行校验
 * @author ly
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
	private Class<? extends Enum> enumValueClass;
	private static final String METHOD_NAME = "fromCode";

	@Override
	public void initialize(EnumValue constraintAnnotation) {
		enumValueClass = constraintAnnotation.value();
		try {
			enumValueClass.getDeclaredMethod(METHOD_NAME, String.class);
		} catch (NoSuchMethodException e){
			throw new IllegalArgumentException("the enum class has not toEnum method", e);
		}
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Method declareMethod;
		try {
			declareMethod = enumValueClass.getDeclaredMethod(METHOD_NAME, String.class);
		} catch (NoSuchMethodException e) {
			return false;
		}

		try {
			Object retObj = declareMethod.invoke(null, value);
			if (retObj == null) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
