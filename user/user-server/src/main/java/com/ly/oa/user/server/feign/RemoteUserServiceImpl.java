package com.ly.oa.user.server.feign;

import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import com.ly.oa.user.server.api.query.UserQuery;
import com.ly.oa.user.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * @author ly
 */
@RestController
@RequestMapping("/user")
public class RemoteUserServiceImpl implements RemoteUserService {

    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Override
    @GetMapping("/page")
    public Page<UserDTO> queryUser(UserQuery userQuery,
                                   @PageableDefault(
                                           value = 20,
                                           sort = { "createTime" },
                                           direction = Sort.Direction.DESC) Pageable pageable) {
        return userService.queryUser(userQuery, pageable);
    }

    @Override
    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @Override
    @GetMapping(params = {"loginName"})
    public UserDTO getUserByLoginName(String loginName) {
        return userService.getUserByLoginName(loginName);
    }

    @Override
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, UserDTO userDTO) {
        userDTO.setId(id);
        return userService.updateUser(userDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @Override
    @PutMapping(value = "/{id}", params = {"action=forbidden"})
    public UserDTO forbiddenUser(@PathVariable Long id) {
        return userService.forbiddenUser(id);
    }

    @Override
    @PutMapping(value = "/{id}", params = {"action=enable"})
    public UserDTO enableUser(@PathVariable Long id) {
        return userService.enableUser(id);
    }
}
