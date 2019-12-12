package com.ly.oa.user.server.entity.dao;

import com.ly.oa.user.server.constant.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ly
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
public class UserEntity {

    @Id
    private String id;

    @Column(
            nullable = false,
            columnDefinition = "varchar(32) comment '姓'"
    )
    private String firstName;

    @Column(
            nullable = false,
            columnDefinition = "varchar(32) comment '名'"
    )
    private String lastName;

    @Column(
            nullable = false,
            columnDefinition = "varchar(32) comment '登录名'"
    )
    private String loginName;

    @Convert(converter = SexEnum.SexEnumConverter.class)
    @Column(
            nullable = false,
            columnDefinition = "varchar(8) default 1 comment '性别'"
    )
    private SexEnum sex;

    @Column(
            nullable = false,
            columnDefinition = "varchar(128) comment '密码'"
    )
    private String password;

    @Column(
            columnDefinition = "varchar(128) comment '邮箱'"
    )
    private String email;

    @CreationTimestamp
    @Column(
            nullable = false,
            updatable = false,
            columnDefinition = "timestamp comment '创建时间'"
    )
    private Date createTime;

    @UpdateTimestamp
    @Column(
            nullable = false,
            columnDefinition = "timestamp comment '更新时间'"
    )
    private Date modifyTime;
}
