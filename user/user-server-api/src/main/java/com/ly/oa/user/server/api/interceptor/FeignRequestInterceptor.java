package com.ly.oa.user.server.api.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		if (StringUtils.equals(Request.HttpMethod.GET.name(), requestTemplate.method())
				&& requestTemplate.requestBody().length() != 0) {

			try {
				JsonNode jsonNode = objectMapper.readTree(requestTemplate.requestBody().asBytes());
				requestTemplate.body(Request.Body.empty());

				Map<String, Collection<String>> queries = new HashMap<>();
				buildQuery(jsonNode, "", queries);
				requestTemplate.queries(queries);
			} catch (Exception e) {
				log.error("【拦截GET请求POJO方式】-出错了：{}", e);
				throw new RuntimeException(e);
			}
		}
	}

	private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
		// 叶子节点
		if (!jsonNode.isContainerNode()) {
			if (jsonNode.isNull()) {
				return;
			}
			Collection<String> values = queries.get(path);
			if (null == values) {
				values = new ArrayList<>();
				queries.put(path, values);
			}
			values.add(jsonNode.asText());
			return;
		}
		// 数组节点
		if (jsonNode.isArray()) {
			Iterator<JsonNode> it = jsonNode.elements();
			while (it.hasNext()) {
				buildQuery(it.next(), path, queries);
			}
		} else {
			Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
			while (it.hasNext()) {
				Map.Entry<String, JsonNode> entry = it.next();
				if (StringUtils.isNotEmpty(path)) {
					buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
				} else {  // 根节点
					buildQuery(entry.getValue(), entry.getKey(), queries);
				}
			}
		}
	}
}
