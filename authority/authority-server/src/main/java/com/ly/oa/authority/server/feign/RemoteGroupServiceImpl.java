package com.ly.oa.authority.server.feign;

import com.ly.oa.authority.server.api.dto.GroupDTO;
import com.ly.oa.authority.server.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ly
 */
@RestController
@RequestMapping("/group")
public class RemoteGroupServiceImpl {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public GroupDTO saveGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.saveGroup(groupDTO);
    }

    @DeleteMapping("/{id}")
    public GroupDTO deleteGroupById(@PathVariable Long id) {
        return groupService.deleteGroup(id);
    }

    @PutMapping("/{id}")
    public GroupDTO updateGroup(@PathVariable Long id, @RequestBody GroupDTO groupDTO) {
        groupDTO.setId(id);
        return groupService.updateGroup(groupDTO);
    }

    @GetMapping("/{id}")
    public GroupDTO findById(@PathVariable Long id) {
        return groupService.findGroupById(id);
    }

    @PutMapping("/user/{userId}/")
    public GroupDTO addUserToGroup(@PathVariable Long userId, @RequestBody GroupDTO groupDTO) {
        return groupService.addUserToGroup(userId, groupDTO);
    }

    @PutMapping("/user/{userIdList}/")
    public GroupDTO addUserListToGroup(@PathVariable List<Long> userIdList, @RequestBody GroupDTO groupDTO) {
        return groupService.addUserListToGroup(userIdList, groupDTO);
    }


    @DeleteMapping("/user/{userId}/")
    public GroupDTO deleteUserToGroup(@PathVariable Long userId, @RequestBody GroupDTO groupDTO) {
        return groupService.deleteUserToGroup(userId, groupDTO);
    }

    @DeleteMapping("/user/{userIdList}/")
    public GroupDTO deleteUserListToGroup(@PathVariable List<Long> userIdList, @RequestBody GroupDTO groupDTO) {
        return groupService.deleteUserListToGroup(userIdList, groupDTO);
    }

}
