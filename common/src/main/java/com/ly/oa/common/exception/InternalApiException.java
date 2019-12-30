package com.ly.oa.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微服务调用间异常
 * @author ly
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternalApiException extends RuntimeException {
	private int code;
	private String message;
}
