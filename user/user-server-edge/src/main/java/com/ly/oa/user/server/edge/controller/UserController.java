package com.ly.oa.user.server.edge.controller;


import com.ly.oa.user.server.api.feign.RemoteUserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final RemoteUserService remoteUserService;

    public UserController(RemoteUserService remoteUserService) {
        this.remoteUserService = remoteUserService;
    }

    @GetMapping("/{id}")
    public Object getUserById(@PathVariable Long id) {
        return remoteUserService.getUserById(id);
    }
}
