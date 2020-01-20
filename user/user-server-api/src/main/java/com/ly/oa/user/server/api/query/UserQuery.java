package com.ly.oa.user.server.api.query;

import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.enums.SexEnum;
import com.ly.oa.user.server.api.validator.EnumValue;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Email;
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
	@Length(max = 32, message = "登录名不能超过32位")
	private String firstName;

	/**
	 * 名
	 */
	@Length(max = 32, message = "登录名不能超过32位")
	private String lastName;

	/**
	 * 登录名
	 */
	@Length(max = 32, message = "登录名不能超过32位")
	private String loginName;

	/**
	 * 是否启用
	 */
	private Boolean isEnable;

	/**
	 * 性别。 可以参考 SexEnum
	 */
	@EnumValue(value = SexEnum.class, message = "性别输入不合法，为无效值！")
	private String sex;

	/**
	 * 邮箱
	 */
	@Email(message = "邮箱格式不正确")
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
