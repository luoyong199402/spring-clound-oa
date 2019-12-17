package com.ly.oa.user.server.feign;

import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ly
 */
@RestController
@RequestMapping("/user")
public class RemoteUserServiceImpl {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDTO saveUser(UserDTO userDTO) {
        return null;
    }

}
