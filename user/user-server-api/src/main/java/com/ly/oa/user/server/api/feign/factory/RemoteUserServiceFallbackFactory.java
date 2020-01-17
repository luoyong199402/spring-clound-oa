package com.ly.oa.user.server.api.feign.factory;

import com.ly.oa.common.exception.InternalApiException;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import com.ly.oa.user.server.api.feign.fallback.RemoteUserServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ly
 */
@Component
@Slf4j
public class RemoteUserServiceFallbackFactory implements FallbackFactory<RemoteUserService> {

	@Override
	public RemoteUserService create(Throwable throwable) {
		log.warn("{}", throwable);
		if (throwable instanceof InternalApiException) {
			throw (InternalApiException) throwable;
		}
		RemoteUserServiceFallbackImpl remoteUserServiceFallback = new RemoteUserServiceFallbackImpl();
		remoteUserServiceFallback.setCause(throwable);
		return remoteUserServiceFallback;
	}
}
