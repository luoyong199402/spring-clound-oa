package com.ly.oa.user.server.edge.controller;


import com.ly.oa.common.util.APIResponse;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        userDTO = remoteUserService.saveUser(userDTO);
        return userDTO;
    }
}
