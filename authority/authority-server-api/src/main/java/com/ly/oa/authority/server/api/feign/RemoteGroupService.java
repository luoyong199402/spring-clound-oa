package com.ly.oa.authority.server.api.feign;

import com.ly.oa.authority.server.api.dto.GroupDTO;
import com.ly.oa.authority.server.api.feign.factory.RemoteGroupServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "oa-user-server", fallbackFactory = RemoteGroupServiceFallbackFactory.class)
public interface RemoteGroupService {

    @RequestMapping(
            value = "/group",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    GroupDTO saveGroup(@RequestBody GroupDTO groupDTO);

    @RequestMapping(
            value = "/group/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    GroupDTO deleteGroupById(@PathVariable Long id);


    @RequestMapping(
            value = "/group/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    GroupDTO updateGroup(@PathVariable Long id, @RequestBody GroupDTO groupDTO);
}
