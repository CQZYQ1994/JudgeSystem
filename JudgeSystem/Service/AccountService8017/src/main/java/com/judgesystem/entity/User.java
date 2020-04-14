package com.judgesystem.entity;

import lombok.Data;

@Data
public class User{
    private int id;
    private String account;
    private String password;
    private String name;
    private String email;
    private String department;
    private String position;
    private Role role;

}
