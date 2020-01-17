package com.ly.oa.user.server.advice;

import com.ly.oa.common.exception.InternalApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestController
@ControllerAdvice
public class SqlExceptionHandler {

	@Resource
	MessageSource messageSource;

	/**
	 * 内部微服务异常统一处理方法
	 */
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public InternalApiException processMicroServiceException(HttpServletResponse response,
															 SQLIntegrityConstraintViolationException e) {
		log.warn("{}", e);
		return new InternalApiException(e, 2000, "数据库唯一键冲突。 请检查传递的数据！");
	}
}
