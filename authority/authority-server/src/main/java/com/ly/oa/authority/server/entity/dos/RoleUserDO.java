package com.ly.oa.authority.server.entity.dos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role_user_mapping")
@Builder
@IdClass(RoleUserDO.class)
public class RoleUserDO implements Serializable {
    
    @Id
    private Integer roleId;

    @Id
    private String userId;
}
