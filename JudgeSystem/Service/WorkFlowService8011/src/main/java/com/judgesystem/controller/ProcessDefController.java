package com.judgesystem.controller;

import com.judgesystem.entity.ProcessDefInfo;
import com.judgesystem.entity.ProcessInsInfo;
import com.judgesystem.entity.TaskHistory;
import com.judgesystem.entity.TaskInfo;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/processDef")
public class ProcessDefController {
    @Autowired
    private ProcessEngine processEngine;
    //查询已部署的流程定义
    @GetMapping("/findProcDef")
    public List<ProcessDefInfo> findProcessDefinition() {
        List<ProcessDefinition> list = processEngine.getRepositoryService()// 与流程定义和部署对象先相关的service
                .createProcessDefinitionQuery()// 创建一个流程定义的查询
                /** 指定查询条件，where条件 */
                // .deploymentId(deploymentId) //使用部署对象ID查询
                // .processDefinitionId(processDefinitionId)//使用流程定义ID查询
                // .processDefinitionNameLike(processDefinitionNameLike)//使用流程定义的名称模糊查询
                /* 排序 */
                .orderByProcessDefinitionVersion().asc()
                // .orderByProcessDefinitionVersion().desc()
                /* 返回的结果集 */
                .list();// 返回一个集合列表，封装流程定义
        // .singleResult();//返回惟一结果集
        // .count();//返回结果集数量
        // .listPage(firstResult, maxResults);//分页查询
        List<ProcessDefInfo> processDefInfoList=new ArrayList<ProcessDefInfo>();

        if (list != null && list.size() > 0) {
            for (ProcessDefinition pd : list) {
                ProcessDefInfo processDefInfo=new ProcessDefInfo();
                processDefInfo.setProcessDefId(pd.getId());
                processDefInfo.setProcessDefName(pd.getName());
                processDefInfo.setProcessDefKey(pd.getKey());
                processDefInfo.setProcessDefDepId(pd.getDeploymentId());
                processDefInfo.setProcessDefVersion(pd.getVersion());
                processDefInfo.setProcessDefResName(pd.getResourceName());
                processDefInfo.setProcessDefDiaResName(pd.getDiagramResourceName());
                processDefInfo.setIsSuspended(pd.isSuspended());
                processDefInfoList.add(processDefInfo);
            }
        }
        return processDefInfoList;

    }
    /**
     * 挂起、激活流程定义
     */
    @GetMapping("/procDef/{state}/{processDefinitionId}")
    public String updateState(@PathVariable("state") String state, @PathVariable("processDefinitionId") String processDefinitionId) {
        String str=null;
        if (state.equals("active")) {
            processEngine.getRepositoryService().activateProcessDefinitionById(processDefinitionId, true, null);
             str= "激活流程定义id："+processDefinitionId;
        } else if (state.equals("suspend")) {
            processEngine.getRepositoryService().suspendProcessDefinitionById(processDefinitionId, true, null);
            str="挂起流程定义id："+processDefinitionId;
        }
        return str;
    }


}

