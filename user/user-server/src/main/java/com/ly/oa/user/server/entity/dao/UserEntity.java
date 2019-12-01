package com.ly.oa.user.server.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "user"
)
public class UserEntity {

    @Id
    private String id;

    private String loginName;

    private String displayName;

    private String password;

    private String email;

    private String telPhone;

    private String mobilePhone;

    private String remark;
}
