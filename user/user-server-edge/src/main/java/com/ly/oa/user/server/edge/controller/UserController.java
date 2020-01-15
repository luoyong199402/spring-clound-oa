package com.ly.oa.user.server.edge.controller;


import com.ly.oa.common.page.Page;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import com.ly.oa.user.server.api.query.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author ly
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController  {

    @Autowired
    private RemoteUserService remoteUserService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        UserDTO userDTO = remoteUserService.getUserById(id);
        return userDTO;
    }

    @RequestMapping(
            value = "/page",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<UserDTO> queryUser(UserQuery userQuery, Pageable pageable) {
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

    @RequestMapping(
            value = "/page2",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<UserDTO> queryUser2(UserQuery userQuery) {
        Page<UserDTO> userDTOS = remoteUserService.queryUser(userQuery);
        log.info("{}", userDTOS);
        return userDTOS;
    }

    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        userDTO = remoteUserService.saveUser(userDTO);
        return userDTO;
    }
}
