package com.yjxxt.pojo;

import java.util.Objects;

public class User {
    private Integer id;
    private String userName;
    private String password;
    private String nick;
    private String mood;
    private String head;

    public User() {
    }

    public User(Integer id, String userName, String password, String nick, String mood, String head) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nick = nick;
        this.mood = mood;
        this.head = head;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                ", mood='" + mood + '\'' +
                ", head='" + head + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
