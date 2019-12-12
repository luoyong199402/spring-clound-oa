package com.ly.oa.user.server.constant.enums;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SexEnumTest {

	@Test
	void testToString() {
		SexEnum sexEnum = SexEnum.valueOf("MAIL");
		log.info("sexEnum = {}", sexEnum.toString());
	}
}