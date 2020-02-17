package com.ly.oa.user.server.api.feign;


import com.ly.oa.common.page.Page;
import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.factory.RemoteUserServiceFallbackFactory;
import com.ly.oa.user.server.api.query.UserQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author ly
 */
@FeignClient(name = "oa-user-server", fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO getUserById(@PathVariable Long id);


    @RequestMapping(
            value = "/user/page",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    Page<UserDTO> queryUser(
            @RequestParam("id") Long id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("loginName") String loginName,
            @RequestParam("isEnable") Boolean isEnable,
            @RequestParam("email") String email,
            @RequestParam("createTimeStartTime") Date createTimeStartTime,
            @RequestParam("createTimeEndTime") Date createTimeEndTime,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer integer,
            @RequestParam("sort") List<String> sort
    );

    @RequestMapping(
            value = "/user/page",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    Page<UserDTO> queryUser(@RequestBody UserQuery userQuery);

    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO saveUser(@RequestBody UserDTO userDTO);

    @RequestMapping(
            value = "/user/login-name/{loginName}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO getUserByLoginName(@PathVariable String loginName);

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
            value = "/user/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"action=forbidden"}
    )
    UserDTO forbiddenUser(@PathVariable Long id);

    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"action=enable"}
    )
    UserDTO enableUser(@PathVariable Long id);
}
