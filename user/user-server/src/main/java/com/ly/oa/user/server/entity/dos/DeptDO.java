package com.ly.oa.user.server.entity.dos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author ly
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dept", uniqueConstraints = {@UniqueConstraint(columnNames= {"code"})})
@Builder
public class DeptDO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(
			nullable = false,
			columnDefinition = "varchar(128) comment '部门名称'"
	)
	private String name;

	@Column(
			nullable = false,
			columnDefinition = "varchar(32) comment '部门简称'"
	)
	private String shortName;

	@Column(
			nullable = false,
			columnDefinition = "varchar(8) comment '排序号'"
	)
	private String sort;

	@Column(
			nullable = false,
			columnDefinition = "varchar(16) comment '部门编码'"
	)
	private String code;

	@Column(
			columnDefinition = "varchar(255) comment '父部门id'"
	)
	private String parentId;

	@Column(
			columnDefinition = "varchar(258) comment '部门地址'"
	)
	private String address;

	@Column(
			columnDefinition = "varchar(16) comment '部门电话'"
	)
	private String tel;

	@Column(
			nullable = false,
			columnDefinition = "tinyint unsigned comment '是否启用'"
	)
	private Boolean isEnable;

	@ManyToMany(mappedBy = "deptList")
	private Set<UserDO> userList;

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
