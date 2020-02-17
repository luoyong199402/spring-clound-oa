package com.ly.oa.user.server.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.enums.SexEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class lTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void getUserById() throws Exception {
		String url = "/user/1";
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		// 返回结果
		String content = response.getContentAsString();
		log.info(content);
	}

	@Test
	void saveUser() throws Exception {
		String url = "/user";
		UserDTO userDTO = getUserDTO();

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders
						.post(url)
						.content(objectMapper.writeValueAsString(userDTO))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		// 返回结果
		String content = response.getContentAsString();
		log.info(content);
	}

	@Test
	void queryUser() throws Exception {
		String url = "/user/page";
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)
						.param("firstName", "1")
						.param("page", "1")
						.param("size", "2")
						.param("sort", "id,desc")
						.param("sort", "loginName")
				)
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		// 返回结果
		String content = response.getContentAsString();
		log.info(content);
	}

	@Test
	void getUserByLoginName() throws Exception {
		String url = "/user";
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON).param("loginName", "1"))
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		// 返回结果
		String content = response.getContentAsString();
		log.info(content);
	}

	@Test
	void updateUser() throws Exception {
		UserDTO userDTO = getUserDTO();
		String url = "/user/1";
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userDTO)))
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		// 返回结果
		String content = response.getContentAsString();
		log.info(content);
	}

	@Test
	void deleteUser() throws Exception {
		String url = "/user/1";
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.delete(url).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		// 返回结果
		String content = response.getContentAsString();
		log.info(content);
	}

	@Test
	void forbiddenUser() throws Exception {
		String url = "/user/208";
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON).param("action", "forbidden"))
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		// 返回结果
		String content = response.getContentAsString();
		log.info(content);
	}

	@Test
	void enableUser() throws Exception {
		String url = "/user/1";
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON).param("action", "enable"))
				.andExpect(status().isOk())
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		// 返回结果
		String content = response.getContentAsString();
		log.info(content);
	}

	private UserDTO getUserDTO() {
		return UserDTO.builder()
				.email("1@1.com")
				.sex(SexEnum.MAIL.getValue())
				.isEnable(true)
				.loginName("admin")
				.firstName("1")
				.lastName("1")
				.password("123456")
				.build();
	}
}