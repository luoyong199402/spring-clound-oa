package com.ly.oa.user.server.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dept")
@Builder
public class DeptEntity {
	@Id
	private String id;

	private String name;

	private String shortName;

	private String sort;

	private String code;

	private String parentId;

	private String address;

	private String tel;

	private String phoneNumber;

	private boolean enable;



}
