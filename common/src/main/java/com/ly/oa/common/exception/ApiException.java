package com.ly.oa.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * api调用异常
 * @author ly
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiException extends RuntimeException {
	private int httpStatus;
	private int apiResultStatus;
	private String messageResourceName;
}
