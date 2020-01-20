package com.ly.oa.user.server.edge.controller;


import com.ly.oa.common.page.Page;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import com.ly.oa.user.server.api.query.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author ly
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private RemoteUserService remoteUserService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        UserDTO userDTO = remoteUserService.getUserById(id);
        return userDTO;
    }

    @GetMapping("page2")
    Page<UserDTO> queryUser2(UserQuery userQuery, Pageable pageable) {
        List<String> sortList = pageable.getSort().stream()
                .map(order -> {
                    return order.getProperty() + "," + order.getDirection().toString();
                })
                .collect(Collectors.toList());

        Page<UserDTO> userDTOS = remoteUserService.queryUser(
                userQuery.getId(),
                userQuery.getFirstName(),
                userQuery.getLastName(),
                userQuery.getLoginName(),
                userQuery.getIsEnable(),
                userQuery.getEmail(),
                userQuery.getCreateTimeStartTime(),
                userQuery.getCreateTimeEndTime(),
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sortList);

        log.info("{}", userDTOS);
        return userDTOS;
    }

    @GetMapping("/page")
    Page<UserDTO> queryUser(@Validated UserQuery userQuery) {
        Page<UserDTO> userDTOS = remoteUserService.queryUser(userQuery);
        log.info("{}", userDTOS);
        return userDTOS;
    }

    @PostMapping
    public UserDTO saveUser(@Validated({UserDTO.Save.class}) @RequestBody UserDTO userDTO) {
        userDTO = remoteUserService.saveUser(userDTO);
        return userDTO;
    }

    @GetMapping(params = {"loginName"})
    public UserDTO getUserByLoginName(String loginName) {
        return remoteUserService.getUserByLoginName(loginName);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        return remoteUserService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable Long id) {
        return remoteUserService.deleteUser(id);
    }

    @PutMapping(value = "/{id}", params = {"action=forbidden"})
    public UserDTO forbiddenUser(@PathVariable Long id) {
        return remoteUserService.forbiddenUser(id);
    }

    @PutMapping(value = "/{id}", params = {"action=enable"})
    public UserDTO enableUser(@PathVariable Long id) {
        return remoteUserService.enableUser(id);
    }
}
