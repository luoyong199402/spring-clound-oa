package com.ly.oa.user.server.api.feign;


import com.ly.oa.user.server.api.dto.UserDTO;
import com.ly.oa.user.server.api.feign.factory.RemoteUserServiceFallbackFactory;
import com.ly.oa.user.server.api.query.UserQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
            value = "/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<UserDTO> queryUser(@RequestParam UserQuery userQuery, @RequestParam Pageable pageable);

    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDTO saveUser(@RequestBody UserDTO userDTO);

    @RequestMapping(
            value = "/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"loginName"}
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
            value = "/user/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"action=enable"}
    )
    UserDTO forbiddenUser(@PathVariable Long id);

    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"action=forbidden"}
    )
    UserDTO enableUser(@PathVariable Long id);


}
