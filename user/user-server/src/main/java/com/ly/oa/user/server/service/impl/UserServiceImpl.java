package com.ly.oa.user.server.service.impl;

import com.ly.oa.common.orika.OrikaBeanMapper;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.query.UserQuery;
import com.ly.oa.user.server.dao.UserDao;
import com.ly.oa.user.server.entity.dos.UserDO;
import com.ly.oa.user.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Optional;

/**
 * @author ly
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrikaBeanMapper orikaBeanMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Cacheable
	public UserDTO getUserById(Long userId) {
		Optional<UserDO> userDoOptional = userDao.findById(userId);
		return orikaBeanMapper.map(userDoOptional.get(), UserDTO.class);
	}

	@Override
	public UserDTO getUserByLoginName(String loginName) {
		Optional<UserDO> userDOOptional = userDao.getByLoginName(loginName);
		return orikaBeanMapper.map(userDOOptional.get(), UserDTO.class);
	}

	@Override
	public Page<UserDTO> queryUser(UserQuery userQuery) {
		SingularAttribute<UserDO, String> loginName;
		EntityManager em = null;
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<UserDO> criteriaQuery = criteriaBuilder.createQuery(UserDO.class);

		Root<UserDO> employee = criteriaQuery.from(UserDO.class);
		Predicate condition = null;

		criteriaQuery.where(condition);

		TypedQuery<UserDO> typedQuery = em.createQuery(criteriaQuery);

		List<UserDO> result = typedQuery.getResultList();

		return null;
	}

	public Page<UserDTO> queryUser(UserDO userDO) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact())
				.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("loginName", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("isEnable", ExampleMatcher.GenericPropertyMatchers.exact())
				.withMatcher("sex", ExampleMatcher.GenericPropertyMatchers.exact())
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
				.withIgnoreNullValues();

		Example<UserDO> example = Example.of(userDO, exampleMatcher);
		List<UserDO> userDoList = userDao.findAll(example);
		List<UserDTO> userDTOS = orikaBeanMapper.mapAsList(userDoList, UserDTO.class);
		Page page = new PageImpl(userDTOS);
		return page;
	}

	@Override
	@CachePut(key = "#result.id")
	public UserDTO saveUser(UserDTO userDTO) {
		UserDO userDO = orikaBeanMapper.map(userDTO, UserDO.class);
		userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
		userDO.setId(null);
		userDO = userDao.save(userDO);
		return orikaBeanMapper.map(userDO, UserDTO.class);
	}

	@Override
	@CachePut(key = "#userDTO.id")
	public UserDTO updateUser(UserDTO userDTO) {
		Optional<UserDO> userDOOptional = userDao.findById(userDTO.getId());
		UserDO updateUserDO = orikaBeanMapper.map(userDTO, UserDO.class);

		updateUserDO.setPassword(userDOOptional.get().getPassword());
		updateUserDO.setIsEnable(userDOOptional.get().getIsEnable());

		UserDO updateUser = userDao.save(updateUserDO);
		return orikaBeanMapper.map(updateUser, UserDTO.class);
	}

	@Override
	@CacheEvict
	public UserDTO deleteUser(Long userId) {
		UserDTO userDTO = getUserById(userId);
		userDao.deleteById(userId);
		return userDTO;
	}

	@Override
	@CachePut(key = "#result.id")
	public UserDTO forbiddenUser(Long userId) {
		Optional<UserDO> userDOOptional = userDao.findById(userId);
		userDOOptional.get().setIsEnable(false);
		UserDO updateUser = userDao.saveAndFlush(userDOOptional.get());
		return orikaBeanMapper.map(updateUser, UserDTO.class);
	}

	@Override
	@CachePut(key = "#result.id")
	public UserDTO enableUser(Long userId) {
		Optional<UserDO> userDOOptional = userDao.findById(userId);
		userDOOptional.get().setIsEnable(true);
		UserDO updateUser = userDao.save(userDOOptional.get());
		return orikaBeanMapper.map(updateUser, UserDTO.class);
	}

	@Override
	@CacheEvict(allEntries=true)
	public void clearCache() {
	}
}
