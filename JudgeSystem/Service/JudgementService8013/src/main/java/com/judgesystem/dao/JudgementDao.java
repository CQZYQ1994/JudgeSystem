package com.judgesystem.dao;

import com.judgesystem.entity.Judgement;

import java.util.List;

public interface JudgementDao {
    public List<Judgement> findAll(int index, int limit);
    public int count();
    public Judgement findById(int id);
    public void save(Judgement judgement);
    public void update(Judgement judgement);
    public void deleteById(int id);
}
