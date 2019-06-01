package com.itheima.domain;

import java.io.Serializable;

public class QueryVo implements Serializable {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "user=" + user +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("helloWorld");
    }
}
