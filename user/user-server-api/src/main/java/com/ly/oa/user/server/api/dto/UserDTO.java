package com.ly.oa.user.server.api.dto;

import com.ly.oa.user.server.api.enums.SexEnum;
import com.ly.oa.user.server.api.validator.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ly
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 姓
     */
    @NotBlank(message = "姓不能为空", groups = {Save.class, Update.class})
    @Length(max = 32, message = "姓不能超过32位", groups = {Save.class, Update.class})
    private String firstName;

    /**
     * 名
     */
    @NotBlank(message = "名不能为空", groups = {Save.class, Update.class})
    @Length(max = 32, message = "姓不能超过32位", groups = {Save.class, Update.class})
    private String lastName;

    /**
     * 登录名
     */
    @NotBlank(message = "名不能为空")
    @Length(max = 32, message = "登录名不能超过32位", groups = {Save.class, Update.class})
    private String loginName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(max = 32, message = "密码不能超过32位", groups = {Save.class, Update.class})
    private String password;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    public static interface Save {};
    public static interface Update {};
}
