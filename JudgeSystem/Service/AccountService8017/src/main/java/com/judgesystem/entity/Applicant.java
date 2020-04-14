package com.judgesystem.entity;

import lombok.Data;

@Data
public class Applicant {
    private int id;
    private String account;
    private String password;
    private String name;
    private String email;
    private String document_path;
}
