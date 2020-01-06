package com.ly.oa.user.server.api.query;

import lombok.Data;

import java.util.Date;

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
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date modifyTime;
}
