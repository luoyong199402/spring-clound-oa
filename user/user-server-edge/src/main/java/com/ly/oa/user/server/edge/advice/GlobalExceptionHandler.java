package com.ly.oa.user.server.edge.advice;

import com.ly.oa.common.exception.ApiException;
import com.ly.oa.common.exception.InternalApiException;
import com.ly.oa.common.util.APIResponse;
import com.netflix.client.ClientException;
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

	@ExceptionHandler({org.springframework.web.bind.MissingServletRequestParameterException.class})
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public APIResponse processRequestParameterException(HttpServletRequest request,
														HttpServletResponse response,
														MissingServletRequestParameterException e) {
		APIResponse result = new APIResponse();
		result.setCode(HttpStatus.FORBIDDEN.value());
		result.setMessage(
				messageSource.getMessage(HttpStatus.FORBIDDEN.getReasonPhrase(),
						null, LocaleContextHolder.getLocale()) + e.getParameterName());
		return result;
	}

	@ExceptionHandler(ApiException.class)
	@ResponseBody
	public APIResponse processApiException(HttpServletResponse response,
										   ApiException e) {
		APIResponse result = new APIResponse();
		response.setStatus(e.getHttpStatus());
		response.setContentType("application/json;charset=UTF-8");
		result.setCode(e.getApiResultStatus());
		String message = messageSource.getMessage(e.getMessageResourceName(),
				null, LocaleContextHolder.getLocale());
		result.setMessage(message);
		return result;
	}

	/**
	 * 内部微服务异常统一处理方法
	 */
	@ExceptionHandler(InternalApiException.class)
	@ResponseStatus(HttpStatus. INTERNAL_SERVER_ERROR)
	@ResponseBody
	public APIResponse processMicroServiceException(HttpServletResponse response,
													InternalApiException e) {
		response.setContentType("application/json;charset=UTF-8");
		APIResponse result = new APIResponse();
		result.setCode(e.getCode());
		result.setMessage(e.getMessage());
		return result;
	}

	/**
	 * 内部微服务异常统一处理方法
	 */
	@ExceptionHandler(HystrixRuntimeException.class)
	@ResponseStatus(HttpStatus. INTERNAL_SERVER_ERROR)
	@ResponseBody
	public APIResponse processMicroServiceException(HttpServletResponse response,
													HystrixRuntimeException e) {
		response.setContentType("application/json;charset=UTF-8");
		APIResponse result = new APIResponse();
		result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		result.setMessage(e.getMessage());

		if (e.getCause() instanceof InternalApiException) {
			InternalApiException internalApiException = (InternalApiException) e.getCause();
			result.setCode(internalApiException.getCode());
			result.setMessage(internalApiException.getMessage());
			return result;
		}

		return result;
	}
}
