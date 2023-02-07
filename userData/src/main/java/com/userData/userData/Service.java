package com.userData.userData;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    UserRepository repo;
    public User addData(User user){
        return repo.save(user);
    }

    public List<User> getData(){
        List<User> list=new ArrayList<>();
        repo.findAll().forEach(u-> list.add(u));
        return list;
    }
}
