package com.judgesystem.controller;

import com.judgesystem.entity.ApplyInfo;
import com.judgesystem.entity.TaskHistory;
import com.judgesystem.entity.TaskInfo;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewBussController {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private TaskService taskService;
    //根据流程定义key启动流程实例，并传入业务id和用户名，由申请人完成第一个申请提交任务
    @GetMapping("/startFlow/{procDefKey}/{businessKey}/{username}")
    public String startReviewBusiness(@PathVariable("procDefKey") String procDefKey, @PathVariable("businessKey") String businessKey, @PathVariable("username") String username){
       Map<String,Object> vars=new HashMap<>();
       vars.put("apply_user",username);
        ProcessInstance pi=processEngine.getRuntimeService()
                .startProcessInstanceByKey(procDefKey,businessKey,vars);
        Task applyTask=taskService.createTaskQuery().processInstanceId(pi.getId())
                .singleResult();
        taskService.setAssignee(applyTask.getId(),username);
        Map<String,Object> args=new HashMap<>();
        args.put("businessKey",businessKey);
        args.put("deal_user",username);
        taskService.complete(applyTask.getId(),args);
        return pi.getId();
    }

    //根据用户角色查询用户当前可进行的任务
    @GetMapping("/currentTask/{userRole}")
    public List<TaskInfo> findCurrentTask(@PathVariable("userRole") String userRole){
        List<Task> list=taskService.createTaskQuery()
                //.processDefinitionKey(procDefKey)
                .taskCandidateGroup(userRole)
                .list();
        List<TaskInfo> taskInfoList=new ArrayList<>();
        if (list!=null&&list.size()>0){
            for (Task task:list){
                TaskInfo taskInfo=new TaskInfo();
                taskInfo.setTaskId(task.getId());
                taskInfo.setTaskName(task.getName());
                taskInfo.setTaskCreateTime(task.getCreateTime().toString());
                taskInfo.setTaskAssignee(task.getAssignee());
                taskInfo.setTaskExecutionId(task.getExecutionId());
                taskInfo.setTaskProcInsId(task.getProcessInstanceId());
                taskInfo.setTaskProcDefId(task.getProcessDefinitionId());
                taskInfo.setBusinessKey(getBusinessKeyByTaskId(task.getId()));
                taskInfoList.add(taskInfo);

            }
        }

        return taskInfoList;
    }
    public String getBusinessKeyByTaskId(String taskId){
        Task task = processEngine
                .getTaskService()
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        ProcessInstance pi = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        return pi.getBusinessKey();
    }

    //根据任务id完成任务，并传入用户名和评审结果作为任务参数
    @GetMapping("/comTask/{taskId}/{username}/{result}")
    public String completeTask(@PathVariable("taskId") String taskId,@PathVariable("username") String username,@PathVariable("result") String result){
        Map<String,Object> vars=new HashMap<>();
        vars.put("deal_user",username);
        vars.put("review_result",result);
        taskService.setAssignee(taskId,username);
        taskService.complete(taskId,vars);
        return "完成任务：任务ID:" + taskId;
    }

    //根据用户名查询用户历史任务
    @GetMapping("/findHisTask/{username}")
    public List<TaskHistory> findHistoryTask(@PathVariable("username") String taskAssignee){

        List<HistoricTaskInstance> list = processEngine.getHistoryService()//与历史数据（历史表）相关的service
                .createHistoricTaskInstanceQuery()//创建历史任务实例查询
                //.processInstanceId(processInstanceId)
                .taskAssignee(taskAssignee)//指定历史任务的办理人
                .list();
        List<TaskHistory> taskHistoryList=new ArrayList<>();
        if(list!=null && list.size()>0){
            for(HistoricTaskInstance hti:list){
                TaskHistory taskHistory=new TaskHistory();
                taskHistory.setTaskHisId(hti.getId());
                taskHistory.setTaskHisName(hti.getName());
                taskHistory.setTaskProcInsId(hti.getProcessInstanceId());
                taskHistory.setTaskStartTime(hti.getStartTime().toString());
                taskHistory.setTaskEndTime(hti.getEndTime().toString());
                taskHistory.setTaskDurationInMillis(hti.getDurationInMillis().toString());
                taskHistory.setTaskUser(hti.getTaskLocalVariables().toString());
                taskHistoryList.add(taskHistory);

            }
        }
        return taskHistoryList;

    }
}
