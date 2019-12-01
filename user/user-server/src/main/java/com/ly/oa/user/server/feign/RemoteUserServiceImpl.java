package com.ly.oa.user.server.feign;

import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.UserDataHandler;

@RestController
@RequestMapping("/user")
public class RemoteUserServiceImpl {

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable String id) {
        return UserDTO.builder()
                .id(id)
                .name("luoyong")
                .sex(1)
                .build();
    }
}
