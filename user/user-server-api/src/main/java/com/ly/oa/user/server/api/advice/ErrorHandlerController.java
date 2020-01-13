package com.ly.oa.user.server.api.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.oa.common.exception.InternalApiException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ErrorHandlerController implements ErrorDecoder {

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() != HttpStatus.OK.value()) {
			String errorContent;
			try {
				errorContent = IOUtils.toString(response.body().asInputStream());
				log.info("errorContent = {}", errorContent);
				InternalApiException internalApiException = objectMapper.readValue(errorContent, InternalApiException.class);
				return internalApiException;
			} catch (Exception e) {
				return new InternalApiException(500, "unknown error");
			}
		}
		return new InternalApiException(500, "unknown error");
	}
}
