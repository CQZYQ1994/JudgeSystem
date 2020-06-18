package com.judgesystem.entity;

import lombok.Data;

import java.util.Date;
@Data
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String postId;
    private String name;
    private String gender;
    private Date birthday;
    private String nation;
    private String nativePlace;
    private String email;
    private String department;
    private String position;
}
