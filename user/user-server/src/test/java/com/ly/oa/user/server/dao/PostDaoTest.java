package com.ly.oa.user.server.dao;

import com.google.common.collect.Lists;
import com.ly.oa.user.server.constant.enums.SexEnum;
import com.ly.oa.user.server.entity.dos.PostDO;
import com.ly.oa.user.server.entity.dos.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback
class PostDaoTest {

	@Autowired
	private PostDao postDao;

	@Autowired
	private UserDao userDao;

	@Test
	public void save() {
		PostDO postDO = getPost();
		postDO = postDao.save(postDO);
		Assert.notNull(postDO.getId(), "保存失败！");
	}

	@Test
	public void delete() {
		save();
		postDao.deleteByCode("1");
		Optional<PostDO> optional = postDao.getByCode("1");
		Assert.isTrue(optional.equals(Optional.empty()), "删除失败！");
	}

	@Test
	public void modify() {
		save();
		Optional<PostDO> optional = postDao.getByCode("1");
		optional.get().setName("new_name");
		PostDO savePostDO = postDao.save(optional.get());

		Assert.isTrue(StringUtils.equals(savePostDO.getName(), "new_name"), "修改操作失败！");
	}

	@Test
	public void find() {
		save();
		Optional<PostDO> byCode = postDao.getByCode("1");
		Assert.notNull(byCode.get(), "查询失败！");
	}

	@Test
	public void findDeptOfUser() {
		UserDO user = getUser();

		PostDO postDO = getPost();
		List<PostDO> postList = Lists.newArrayList(postDO);
		user.setPostList(postList);

		postDao.save(postDO);
		userDao.save(user);

		Optional<PostDO> byCode = postDao.getByCode(postDO.getCode());
		log.info("postDO = {}", byCode.get());
		Assert.notNull(byCode.get().getId(), "级联查询失败！");

	}

	private UserDO getUser() {
		UserDO user = UserDO.builder()
				.firstName("luo")
				.lastName("yong")
				.password("123")
				.loginName("ly2")
				.sex(SexEnum.MAIL).build();
		return user;
	}

	private PostDO getPost() {
		return PostDO.builder()
				.name("测试岗位")
				.code("1").build();
	}
}