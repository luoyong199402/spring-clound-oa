package com.ly.oa.user.server.api.feign;


import com.ly.oa.user.server.api.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lyong
 */
@FeignClient(name = "oa-user-server")
public interface RemoteUserService {

    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO getUserById(@PathVariable Long id);

    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO saveUser(@RequestBody UserDTO userDTO);
}
