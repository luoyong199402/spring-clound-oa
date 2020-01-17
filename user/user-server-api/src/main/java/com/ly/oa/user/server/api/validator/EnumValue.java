package com.ly.oa.user.server.api.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * @author ly
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = EnumValueValidator.class)
@ReportAsSingleViolation
public @interface EnumValue {
	String message() default "enum is invalid";

	Class<?>[] groups() default {};

	Class<? extends Enum> value();

	Class<? extends Payload>[] payload() default {};

	// 同时指定多个时使用
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		EnumValue[] value();
	}
}
