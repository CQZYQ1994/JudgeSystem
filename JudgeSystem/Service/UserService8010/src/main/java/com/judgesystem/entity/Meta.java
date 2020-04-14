package com.judgesystem.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Meta implements Serializable {
    private Boolean keep_alive;
    private Boolean require_auth;
}
