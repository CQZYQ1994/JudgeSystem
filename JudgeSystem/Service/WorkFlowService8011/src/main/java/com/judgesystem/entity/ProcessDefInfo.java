package com.judgesystem.entity;

import lombok.Data;

@Data
public class ProcessDefInfo {
    private String processDefId;
    private String processDefName;
    private String processDefKey;
    private int processDefVersion;
    private String processDefResName;
    private String processDefDiaResName;
    private String processDefDepId;
    private boolean isSuspended;

    public boolean getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public void setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
    }

    public String getProcessDefName() {
        return processDefName;
    }

    public void setProcessDefName(String processDefName) {
        this.processDefName = processDefName;
    }

    public String getProcessDefKey() {
        return processDefKey;
    }

    public void setProcessDefKey(String processDefKey) {
        this.processDefKey = processDefKey;
    }

    public int getProcessDefVersion() {
        return processDefVersion;
    }

    public void setProcessDefVersion(int processDefVersion) {
        this.processDefVersion = processDefVersion;
    }

    public String getProcessDefResName() {
        return processDefResName;
    }

    public void setProcessDefResName(String processDefResName) {
        this.processDefResName = processDefResName;
    }

    public String getProcessDefDiaResName() {
        return processDefDiaResName;
    }

    public void setProcessDefDiaResName(String processDefDiaResName) {
        this.processDefDiaResName = processDefDiaResName;
    }

    public String getProcessDefDepId() {
        return processDefDepId;
    }

    public void setProcessDefDepId(String processDefDepId) {
        this.processDefDepId = processDefDepId;
    }
}
