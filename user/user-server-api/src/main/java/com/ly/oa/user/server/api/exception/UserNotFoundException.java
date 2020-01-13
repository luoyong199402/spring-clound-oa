package com.ly.oa.user.server.api.exception;

import com.ly.oa.common.exception.ApiException;
import com.ly.oa.common.exception.InternalApiException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 用户没有找到异常
 * @author ly
 */
@Getter
@Setter
public class UserNotFoundException extends InternalApiException {
	private Long userId;

	public UserNotFoundException(Throwable cause, String message, Long userId) {
		super(cause, HttpStatus.BAD_REQUEST.value(), message);
		setUserId(userId);
	}
}
