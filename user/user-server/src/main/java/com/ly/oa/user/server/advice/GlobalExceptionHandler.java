package com.ly.oa.user.server.advice;

import com.ly.oa.common.exception.ApiException;
import com.ly.oa.common.exception.InternalApiException;
import com.ly.oa.common.util.APIResponse;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ly
 */
@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {


	@Resource
	MessageSource messageSource;

	/**
	 * 内部微服务异常统一处理方法
	 */
	@ExceptionHandler(InternalApiException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public InternalApiException processMicroServiceException(HttpServletResponse response,
															 InternalApiException e) {
		log.warn("{}", e);
		return e;
	}
}
