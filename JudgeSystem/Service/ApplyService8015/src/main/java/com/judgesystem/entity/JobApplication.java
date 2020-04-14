package com.judgesystem.entity;

import lombok.Data;

@Data
public class JobApplication {
    private int id;
    private String name;
    private String apply_position;
    private String email;
    private String time1;
    private String school1;
    private String education_level;
    private String time2;
    private String school2;
    private String education_level2;
    private String create_time;
    private String document_path;

}
