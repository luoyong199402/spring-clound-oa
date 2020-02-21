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
@Table(name = "user_group", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
@Builder
public class GroupDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            columnDefinition = "varchar(32) comment '组名称'"
    )
    private String name;

    @Column(
            nullable = false,
            columnDefinition = "varchar(32) comment '组编码'"
    )
    private String code;

    @Column(
            columnDefinition = "bigint comment '父组id'"
    )
    private String parentGroupId;

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
