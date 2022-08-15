package com.yjxxt.service;

import com.yjxxt.pojo.User;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
    private UserService userService=null;

    @Before
    public void init(){
        System.out.println("测试方法执行前执行.......");
        userService=new UserService();
    }

    @Test
    public void listUser(){
        userService.listUser();
    }

    @Test
    public void loginTest(){
        userService.login("admin","123456");
    }

    @Test
    public void addUser(){
        System.out.println("添加前：");
        userService.listUser();
        userService.addUser(new User(3,"xpy","123456","xpy","",""));
        System.out.println("添加后：");
        userService.listUser();
    }

    @Test
    public void updateUser(){
        userService.updateUser(new User(1,"admin","123456","t","",""));
        userService.listUser();
    }

    @Test
    public void delUser(){
        userService.delUser(2);
        userService.listUser();
    }

}
