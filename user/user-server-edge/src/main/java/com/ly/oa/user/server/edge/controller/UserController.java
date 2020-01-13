package com.ly.oa.user.server.edge.controller;


import com.ly.oa.common.util.APIResponse;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import com.ly.oa.user.server.api.query.UserQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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
                null);

        log.info("{}", userDTOS);
        return userDTOS;

    }

    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        userDTO = remoteUserService.saveUser(userDTO);
        return userDTO;
    }
}
