package com.ly.oa.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth-client")
public class ClientController {

    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ClientDetails addClient(@RequestBody BaseClientDetails baseClientDetails) {
        String clientSecret = passwordEncoder.encode(baseClientDetails.getClientSecret());
        baseClientDetails.setClientSecret(clientSecret);

        jdbcClientDetailsService.addClientDetails(baseClientDetails);
        return baseClientDetails;
    }
}
