package com.ly.oa.oauth.service;

import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.RemoteUserService;
import com.ly.oa.user.server.api.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = remoteUserService.getUserByLoginName(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("没有查询到用户");
        }

        return new User(username,
                userDTO.getPassword(),
                userDTO.getIsEnable(),
                true,
                true,
                true,
                getGrantedAuthority());
    }

    public List<GrantedAuthority> getGrantedAuthority(){
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return list;
    }

}
