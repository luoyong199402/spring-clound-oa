package com.ly.oa.user.server.edge.controller;


import com.ly.oa.user.server.api.feign.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RemoteUserService remoteUserService;

    @GetMapping("/{id}")
    public Object getUserById(@PathVariable Long id) {
        return remoteUserService.getUserById(id);
    }
}
