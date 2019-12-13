package com.ly.oa.user.server.config;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author ly
 */
public class CustomIDGenerator extends IdentityGenerator {
	@Override
	public Serializable generate(SharedSessionContractImplementor s, Object obj) {
		return UUID.randomUUID().toString();
	}
}
