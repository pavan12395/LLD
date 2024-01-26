package org.example.service;


import org.example.models.Group;
import org.example.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupService {
    private Map<String,Group> groupMap;

    private static GroupService instance;

    public static GroupService getInstance(){
        synchronized (GroupService.class){
            if(instance == null){
                synchronized (GroupService.class){
                    instance = new GroupService();
                }
            }
        }
        return instance;
    }

    public GroupService(){
        groupMap = new HashMap<>();
    }

    public static void setInstance(GroupService GroupService){
        synchronized (GroupService.class){
            instance = GroupService;
        }
    }

    public void addGroup(Group group) throws  Exception {
        if(groupMap.containsKey(group)){
            throw new Exception("Group doesnt exist");
        }
        else {
            List<String> userIds = group.getUserIds();
            for(String userId : userIds){
                if(!UserService.getInstance().contains(userId)){
                    throw new Exception("User doesnt exist");
                }
            }
            groupMap.put(group.getId(),group);
        }
    }

    public boolean contains(String groupId){
        return groupMap.containsKey(groupId);
    }

    public boolean checkUserIdForGroup(String groupId,String userId) throws  Exception {
        if(this.contains(groupId)){
            Group group = groupMap.get(groupId);
            List<String> userIds = group.getUserIds();
            return userIds.contains(userId);
        }
        else {
            throw new Exception("Group doesnt exist!");
        }
    }
}