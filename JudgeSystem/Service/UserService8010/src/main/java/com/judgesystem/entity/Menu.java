package com.judgesystem.entity;

import lombok.Data;

import javax.validation.constraints.Null;
import java.util.List;

@Data
public class Menu {
    private int id;
    private String url;
    private String path;
    private String name;
    private Boolean show;
    private String component;
    private String iconCls;
    private Meta meta;
    private int parent_id;
    private List<Menu> children;
    private List<Role> roles;
}
