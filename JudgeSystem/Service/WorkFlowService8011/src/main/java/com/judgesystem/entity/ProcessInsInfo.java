package com.judgesystem.entity;

import lombok.Data;

@Data
public class ProcessInsInfo {
    private String processInsId;
    private String processDefId;
    private String processInsName;
    private String processInsBusKey;
    private String processInsDepId;
    private String processInsDesc;
    private String processDefKey;
    private String processDefName;
    private Boolean isSuspended;

    public Boolean getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(Boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public void setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
    }

    public String getProcessInsName() {
        return processInsName;
    }

    public void setProcessInsName(String processInsName) {
        this.processInsName = processInsName;
    }

    public String getProcessInsBusKey() {
        return processInsBusKey;
    }

    public void setProcessInsBusKey(String processInsBusKey) {
        this.processInsBusKey = processInsBusKey;
    }

    public String getProcessInsDepId() {
        return processInsDepId;
    }

    public void setProcessInsDepId(String processInsDepId) {
        this.processInsDepId = processInsDepId;
    }

    public String getProcessInsDesc() {
        return processInsDesc;
    }

    public void setProcessInsDesc(String processInsDesc) {
        this.processInsDesc = processInsDesc;
    }

    public String getProcessDefKey() {
        return processDefKey;
    }

    public void setProcessDefKey(String processDefKey) {
        this.processDefKey = processDefKey;
    }

    public String getProcessDefName() {
        return processDefName;
    }

    public void setProcessDefName(String processDefName) {
        this.processDefName = processDefName;
    }
}
