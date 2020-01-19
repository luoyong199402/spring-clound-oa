package com.ly.oa.user.server.api.exception;

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
public class UserAlreadyExistException extends InternalApiException {
	private String loginName;

	public UserAlreadyExistException(Throwable cause, String message, String loginName) {
		super(cause, HttpStatus.BAD_REQUEST.value(), message);
		setLoginName(loginName);
	}

	public UserAlreadyExistException(String loginName) {
		super(null, HttpStatus.BAD_REQUEST.value(), "用户已经存在！");
		setLoginName(loginName);
	}
}
