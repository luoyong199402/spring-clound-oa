package com.ly.oa.authority.server.entity.dos;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
@Builder
public class RoleDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            columnDefinition = "varchar(32) comment '角色名称'"
    )
    private String name;

    @Column(
            nullable = false,
            columnDefinition = "varchar(32) comment '角色编码'"
    )
    private String code;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "role_group_mapping",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")}
    )
    private List<GroupDO> userGroupDOList;

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
