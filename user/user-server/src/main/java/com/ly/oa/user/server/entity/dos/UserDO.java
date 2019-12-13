package com.ly.oa.user.server.entity.dos;

import com.ly.oa.user.server.constant.enums.SexEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author ly
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"loginName"})})
@Builder
public class UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    /**
     * CascadeType.MERGE     会处理关系关系表。 但是不会操作级联的那个张表。 如果级联的那张表没有
     * CascadeType.REMOVE    会将级联的那张表一并删除掉（中间表和级联表）。
     * CascadeType.PERSIST   会处理级联保存（中间表和级联表）, 级联删除（只删除中间表），
     * CascadeType.DETACH    级联脱管/游离操作。  相当于将中间表的关系指点置空。
     * CascadeType.ALL       所有操作
     * CascadeType.REFRESH   级联刷新操作。 会先更新级联表数据。
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_dept",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "dept_id", referencedColumnName = "id")}
    )
    private List<DeptDO> deptList;

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
