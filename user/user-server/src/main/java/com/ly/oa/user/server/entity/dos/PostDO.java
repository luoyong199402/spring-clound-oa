package com.ly.oa.user.server.entity.dos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post", uniqueConstraints = {@UniqueConstraint(columnNames= {"code"})})
@Builder
public class PostDO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(
			nullable = false,
			columnDefinition = "varchar(128) comment '岗位名称'"
	)
	private String name;

	@Column(
			nullable = false,
			columnDefinition = "varchar(16) comment '岗位编码'"
	)
	private String code;

	@CreationTimestamp
	@Column(
			nullable = false,
			updatable = false,
			columnDefinition = "timestamp comment '创建时间'"
	)
	private Date createTime;

	@ManyToMany(mappedBy = "postList")
	private List<UserDO> userList;

	@UpdateTimestamp
	@Column(
			nullable = false,
			columnDefinition = "timestamp comment '更新时间'"
	)
	private Date modifyTime;
}
