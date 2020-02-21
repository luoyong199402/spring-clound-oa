package com.ly.oa.authority.server.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author ly
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 组
     */
    @NotBlank(message = "组名称不能为空", groups = {Save.class, Update.class})
    @Length(max = 32, message = "组名称不能超过32位", groups = {Save.class, Update.class})
    private String name;

    /**
     * 名
     */
    @NotBlank(message = "组编码不能为空", groups = {Save.class, Update.class})
    @Length(max = 32, message = "组编码不能超过32位", groups = {Save.class, Update.class})
    private String code;

    /**
     * 登录名
     */
    @Length(max = 32, message = "父组id不能超过32位", groups = {Save.class, Update.class})
    private Long parentGroupId;

    public static interface Save {};
    public static interface Update {};
}
