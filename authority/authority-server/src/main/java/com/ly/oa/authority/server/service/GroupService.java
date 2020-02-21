package com.ly.oa.authority.server.service;

import com.ly.oa.authority.server.api.dto.GroupDTO;

import java.security.acl.Group;
import java.util.List;

public interface GroupService {
    GroupDTO saveGroup(GroupDTO groupDTO);

    GroupDTO deleteGroup(Long id);

    GroupDTO updateGroup(GroupDTO groupDTO);

    GroupDTO findGroupById(Long id);

    GroupDTO addUserToGroup(Long userId, GroupDTO groupDTO);

    GroupDTO addUserListToGroup(List<Long> idList, GroupDTO groupDTO);

    GroupDTO deleteUserToGroup(Long userId, GroupDTO groupDTO);

    GroupDTO deleteUserListToGroup(List<Long> idList, GroupDTO groupDTO);
}
