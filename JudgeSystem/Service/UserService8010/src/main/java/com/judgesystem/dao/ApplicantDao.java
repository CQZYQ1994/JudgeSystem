package com.judgesystem.dao;

import com.judgesystem.entity.Applicant;

public interface ApplicantDao {
    public Applicant login(String account, String password);
}
