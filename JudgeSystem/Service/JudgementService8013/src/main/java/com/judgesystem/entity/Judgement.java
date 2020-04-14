package com.judgesystem.entity;

import lombok.Data;

@Data
public class Judgement {
    private int id;
    private String applyer;
    private String apply_position;
    private String review_status;
    private String start_time;
    private String end_time;
    private String review_opinion;
    private String review_result;
    private String create_time;
    private String document_path;

}
