package com.judgesystem.entity;

import lombok.Data;

@Data
public class ProcInsHistory {
    private String procInsId;
    private String procInsName;
    private String procInsStartTime;
    private String procInsEndTime;
    private String startUser;

    public String getStartUser() {
        return startUser;
    }

    public void setStartUser(String startUser) {
        this.startUser = startUser;
    }

    public String getProcInsId() {
        return procInsId;
    }

    public void setProcInsId(String procInsId) {
        this.procInsId = procInsId;
    }

    public String getProcInsName() {
        return procInsName;
    }

    public void setProcInsName(String procInsName) {
        this.procInsName = procInsName;
    }

    public String getProcInsStartTime() {
        return procInsStartTime;
    }

    public void setProcInsStartTime(String procInsStartTime) {
        this.procInsStartTime = procInsStartTime;
    }

    public String getProcInsEndTime() {
        return procInsEndTime;
    }

    public void setProcInsEndTime(String procInsEndTime) {
        this.procInsEndTime = procInsEndTime;
    }
}
