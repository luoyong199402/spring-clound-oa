package com.ly.oa.authority.server.service.impl;

import com.ly.oa.authority.server.api.dto.GroupDTO;
import com.ly.oa.authority.server.dao.GroupDao;
import com.ly.oa.authority.server.dao.GroupUserDao;
import com.ly.oa.authority.server.entity.dos.GroupDO;
import com.ly.oa.authority.server.entity.dos.GroupUserDO;
import com.ly.oa.authority.server.service.GroupService;
import com.ly.oa.common.orika.OrikaBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GroupUserDao groupUserDao;

    @Autowired
    private OrikaBeanMapper orikaBeanMapper;

    @Override
    public GroupDTO saveGroup(GroupDTO groupDTO) {
        GroupDO groupDO = orikaBeanMapper.map(groupDTO, GroupDO.class);
        groupDO.setId(null);

        groupDO = groupDao.save(groupDO);
        return orikaBeanMapper.map(groupDO, GroupDTO.class);
    }

    @Override
    public GroupDTO deleteGroup(Long id) {
        GroupDTO groupDTO = findGroupById(id);
        groupDao.deleteById(id);
        return groupDTO;
    }

    @Override
    public GroupDTO updateGroup(GroupDTO groupDTO) {
        GroupDO groupDO = orikaBeanMapper.map(groupDTO, GroupDO.class);
        return orikaBeanMapper.map(groupDO, GroupDTO.class);
    }

    @Override
    public GroupDTO findGroupById(Long id) {
        Optional<GroupDO> groupDOOptional = groupDao.findById(id);
        return orikaBeanMapper.map(groupDOOptional.get(), GroupDTO.class);
    }

    @Override
    public GroupDTO addUserToGroup(Long userId, GroupDTO groupDTO) {
        GroupUserDO groupUserDO = GroupUserDO.builder()
                .groupId(groupDTO.getId())
                .userId(userId).build();

        groupUserDao.save(groupUserDO);
        return null;
    }

    @Override
    public GroupDTO addUserListToGroup(List<Long> idList, GroupDTO groupDTO) {
        idList
                .stream()
                .forEach(id -> {
                    addUserToGroup(id, groupDTO);
                });

        return groupDTO;
    }

    @Override
    public GroupDTO deleteUserToGroup(Long userId, GroupDTO groupDTO) {
        GroupUserDO groupUserDO = GroupUserDO.builder()
                .groupId(groupDTO.getId())
                .userId(userId).build();

        groupUserDao.delete(groupUserDO);
        return groupDTO;
    }

    @Override
    public GroupDTO deleteUserListToGroup(List<Long> idList, GroupDTO groupDTO) {
        idList
                .stream()
                .forEach(id -> {
                    deleteUserToGroup(id, groupDTO);
                });

        return groupDTO;
    }
}
