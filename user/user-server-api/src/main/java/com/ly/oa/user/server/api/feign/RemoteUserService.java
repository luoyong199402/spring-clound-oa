package com.ly.oa.user.server.api.feign;


import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.query.UserQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author ly
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
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<UserDTO> queryUser(UserQuery userQuery);

    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO saveUser(@RequestBody UserDTO userDTO);

    @RequestMapping(
            value = "/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO getUserByLoginName(String loginName);

    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO);

    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO deleteUser(@PathVariable Long id);

    @RequestMapping(
            value = "/user/forbiddance/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO forbiddenUser(@PathVariable Long id);

    @RequestMapping(
            value = "/user/{id}/enabler",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO enableUser(@PathVariable Long id);


}
