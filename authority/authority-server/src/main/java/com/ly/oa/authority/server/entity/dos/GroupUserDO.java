package com.ly.oa.authority.server.entity.dos;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_user_mapping")
@Builder
@IdClass(GroupUserDO.class)
public class GroupUserDO implements Serializable {
    @Id
    @GeneratedValue
    private Long groupId;

    @Id
    @GeneratedValue
    private Long userId;
}
