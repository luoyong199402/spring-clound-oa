package com.ly.oa.user.server.api.query;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @author ly
 */
@Data
public class UserQuery {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 姓
	 */
	private String firstName;

	/**
	 * 名
	 */
	private String lastName;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 是否启用
	 */
	private Boolean isEnable;

	/**
	 * 性别。 可以参考 SexEnum
	 */
	private String sex;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 创建时间(开始时间)
	 */
	private Date createTimeStartTime;

	/**
	 * 创建时间结束时间
	 */
	private Date createTimeEndTime;

	/**
	 * 分页的第几页
	 */
	private Integer page;

	/**
	 * 每页的大小
	 */
	private Integer size;

	/**
	 * 排序字段
	 */
	private List<String> sort;
}
