package com.judgesystem.dao;
import com.judgesystem.entity.Meeting;
import java.util.List;

public interface MeetingDao {
    public List<Meeting> findAll(int index, int limit);
    public int count();
    public Meeting findById(int id);
    public void save(Meeting meeting);
    public void update(Meeting meeting);
    public void deleteById(int id);
}
