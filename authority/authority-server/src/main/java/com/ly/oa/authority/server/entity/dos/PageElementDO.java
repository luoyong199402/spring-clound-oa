package com.ly.oa.authority.server.entity.dos;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "page_element", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
@Builder
public class PageElementDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            columnDefinition = "varchar(32) comment '页面元素编码'"
    )
    private String code;

    @Column(
            nullable = false,
            columnDefinition = "varchar(32) comment '页面元素名称'"
    )
    private String name;

    @ManyToMany(mappedBy = "pageElementDOList")
    private List<AuthorityDO> authorityDOList;
}
