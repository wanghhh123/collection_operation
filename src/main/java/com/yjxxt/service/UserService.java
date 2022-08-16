package com.yjxxt.service;

import com.yjxxt.pojo.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> users;

    public UserService() {
        users=new ArrayList<User>();
        users.add(new User(1,"admin","123456","admin","",""));
        users.add(new User(2,"test","123456","test","",""));
    }

    /**
     * 添加用户记录
     * @param user
     */
    public void addUser(User user){
        /**
         * 1.参数检验
         *      用户名 密码 昵称 非空
         * 2.用户名唯一 &昵称唯一
         * 3.执行添加 返回结果
         */
        if(null==user){
            throw new RuntimeException("用户记录不存在");
        }
        if(StringUtils.isBlank(user.getUserName())){
            throw new RuntimeException("用户名不能为空！");
        }
        if(StringUtils.isBlank(user.getPassword())){
            throw new RuntimeException("密码不能为空！");
        }

        /**
         * 用户名 &用户昵称唯一
         */
        for (User user1 : users) {
            if (user1.getUserName().equals(user.getUserName())) {
                throw new RuntimeException("用户名已存在！");
            }
            if (user1.getNick().equals(user.getNick())){
                throw new RuntimeException("用户昵称已存在！");
            }
        }

        if(users.contains(user)){
            throw new RuntimeException("用户已存在！");
        }

        users.add(user);
    }

    /**
     * 修改用户信息
     */
    public void updateUser(User user){
        /**
         * 1.非空校验
         *      用户名 昵称 密码 非空
         * 2.根据id查询用户记录是否存在
         *      -不存在 抛异常
         * 3.记录存在 判断 用户名 昵称 是否重复
         *          用户名 昵称唯一
         * 4.更新用户记录
         */
        if(null==user){
            throw new RuntimeException("用户记录不存在");
        }
        if(StringUtils.isBlank(user.getUserName())){
            throw new RuntimeException("用户名不能为空！");
        }
        if(StringUtils.isBlank(user.getPassword())){
            throw new RuntimeException("密码不能为空！");
        }
        if(StringUtils.isBlank(user.getNick())){
            throw new RuntimeException("昵称不能为空！");
        }
        if(null==user.getId() || null==findUserByUserId(user.getId())){
            throw new RuntimeException("待更新的用户记录不存在！");

        }

        long count = users.stream()
                .filter(u -> u.getUserName().equals(user.getUserName()))
                .filter(u -> !u.getId().equals(user.getId()))
                .count();
        if(count==1){
            throw new RuntimeException("用户名已存在！");
        }
        long count1 = users.stream()
                .filter(u -> u.getNick().equals(user.getNick()))
                .filter(u -> !u.getId().equals(user.getId()))
                .count();
        if(count1==1){
            throw new RuntimeException("用户昵称已存在！");
        }
        users.set(users.indexOf(findUserByUserId(user.getId())),user );
    }



    /**
     * 删除用户信息
     */
    public void delUser(Integer userId){
        /**
         * 1.确定信息是否存在
         *      -不存在，抛异常
         * 2.存在,执行删除
         */
        User result=findUserByUserId(userId);
        if(null==result){
            throw new RuntimeException("待删除的用户信息不存在！");
        }
        users.remove(result);
    }

    /**
     * 用户列表展示
     */
    public void listUser(){
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 用户登录
     * @param username
     * @param password
     */
    public void login(String username,String password){
        /**
         * 1.用户名，密码非空校验
         * 2.根据用户名查找用户记录
         * 3.用户记录是否存在
         *         --不存在，抛异常
         * 4.用户存在
         *          --比对密码,不一样抛异常
         *          --比对成功，登陆成功
         *
         */
        if(StringUtils.isBlank(username)){
            throw new RuntimeException("用户名不能为空！");
        }
        if(StringUtils.isBlank(password)){
            throw new RuntimeException("密码不能为空！");
        }

        Integer index=null;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUserName().equals(username)){
                index=i;
                break;
            }
        }
        if(index==null){
            throw new RuntimeException("用户不存在！");
        }
        if(!users.get(index).getPassword().equals(password)){

            throw new RuntimeException("密码错误！");
        }
        System.out.println("登陆成功");
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * 根据用户id查询信息
     * @param id
     * @return
     */
    public User findUserByUserId(Integer id) {
        Optional<User> optionalUser= users.stream().filter(u -> u.getId().equals(id)).findFirst();
        return optionalUser.isPresent()?optionalUser.get():null;

    }


}
