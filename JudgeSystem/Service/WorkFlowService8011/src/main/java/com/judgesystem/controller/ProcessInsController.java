package com.judgesystem.controller;

import com.judgesystem.entity.ProcInsHistory;
import com.judgesystem.entity.ProcessInsInfo;
import com.judgesystem.entity.TaskHistory;
import com.judgesystem.entity.TaskInfo;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.avalon.framework.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/processIns")
public class ProcessInsController {
    @Autowired
    private ProcessEngine processEngine;

    //使用流程定义key启动流程实例
    @GetMapping("/startProcIns/{processInstanceKey}")
    public ProcessInsInfo processInsCreate(@PathVariable("processInstanceKey") String processInstanceKey) {
        ProcessInstance pi = processEngine.getRuntimeService()// 与正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey(processInstanceKey);//startProcessInstanceByKey(processDefinitionKey);// 使用流程定义的key启动流程实例，key对应HelloWorld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
        ProcessInsInfo processInsInfo=new ProcessInsInfo();
        processInsInfo.setProcessInsId(pi.getId());
        processInsInfo.setProcessInsName(pi.getName());
        processInsInfo.setProcessInsBusKey(pi.getBusinessKey());
        processInsInfo.setProcessInsDepId(pi.getDeploymentId());
        processInsInfo.setProcessInsDesc(pi.getDescription());
        processInsInfo.setProcessDefId(pi.getProcessDefinitionId());
        processInsInfo.setProcessDefKey(pi.getProcessDefinitionKey());
        processInsInfo.setProcessDefName(pi.getProcessDefinitionName());
        processInsInfo.setIsSuspended(pi.isSuspended());
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday", "1001");
        return processInsInfo;
    }

    //查询所有运行中的流程实例
    @GetMapping("/findAllProcIns")
    public List<ProcessInsInfo> findAllProcessIns(){
        List<ProcessInstance> piList=processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .orderByProcessDefinitionId()
                .desc()
                .list();
        List<ProcessInsInfo> processInsInfoList=new ArrayList<>();
        if (piList != null && piList.size() > 0) {
            for(ProcessInstance pi:piList){
                ProcessInsInfo processInsInfo=new ProcessInsInfo();
                processInsInfo.setProcessInsId(pi.getId());
                processInsInfo.setProcessInsName(pi.getName());
                processInsInfo.setProcessInsDesc(pi.getDescription());
                processInsInfo.setProcessInsBusKey(pi.getBusinessKey());
                processInsInfo.setProcessInsDepId(pi.getDeploymentId());
                processInsInfo.setProcessDefId(pi.getDeploymentId());
                processInsInfo.setProcessDefName(pi.getProcessDefinitionName());
                processInsInfo.setProcessDefKey(pi.getProcessDefinitionKey());
                processInsInfo.setIsSuspended(pi.isSuspended());
                processInsInfoList.add(processInsInfo);
            }
        }
        return processInsInfoList;
    }

    //通过流程实例id挂起流程实例
    @GetMapping("/susProcessIns/{processInstanceId}")
    public String processInsCutDown(@PathVariable("processInstanceId") String processInstanceId) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        ProcessInstance processInstance = processInstanceQuery.processInstanceId(processInstanceId).singleResult();
        if (null == processInstance) {
            return "无法挂起已结束的流程";
        } else {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            return "已挂起";
        }
    }

    //通过流程实例id激活流程实例
    @GetMapping("/actProcessIns/{processInstanceId}")
    public ProcessInsInfo activateProcessIns(@PathVariable("processInstanceId") String processInstanceId) {
        processEngine.getRuntimeService().activateProcessInstanceById(processInstanceId);
        ProcessInstanceQuery processInstanceQuery = processEngine.getRuntimeService().createProcessInstanceQuery();
        ProcessInstance pi = processInstanceQuery.processInstanceId(processInstanceId).singleResult();
        ProcessInsInfo procIns=new ProcessInsInfo();
        procIns.setProcessDefId(pi.getProcessDefinitionId());
        procIns.setProcessDefName(pi.getProcessDefinitionName());
        procIns.setProcessDefKey(pi.getProcessDefinitionKey());
        procIns.setProcessInsId(pi.getId());
        procIns.setProcessInsDepId(pi.getDeploymentId());
        procIns.setProcessInsName(pi.getName());
        procIns.setProcessInsDesc(pi.getDescription());
        procIns.setProcessInsBusKey(pi.getBusinessKey());
        procIns.setIsSuspended(pi.isSuspended());
        return procIns;

    }

    //查询流程实例启动了多少次
    @GetMapping("/HisProcessIns/{processInstanceId}")
    public String findHistoryProcessInstance(@PathVariable("processInstanceId") String processInstanceId) {
        HistoricProcessInstance hpi = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        String result = hpi.getId() + "    " + hpi.getProcessDefinitionId() + "   " + hpi.getStartTime() + "   " + hpi.getDurationInMillis();
        return result;
    }
    //查询历史流程记录
    @GetMapping("/procInsHis")
    public List<ProcInsHistory> findProcInsHistory(){
        List<HistoricProcessInstance> list=processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery()
                .finished()
                .list();
        List<ProcInsHistory> procInsHistoryList=new ArrayList<>();
        if(list!=null && list.size()>0){
            for (HistoricProcessInstance hi:list){
                ProcInsHistory ph=new ProcInsHistory();
                ph.setProcInsId(hi.getId());
                ph.setProcInsName(hi.getName());
                ph.setStartUser(hi.getStartUserId());
                ph.setProcInsStartTime(hi.getStartTime().toString());
                ph.setProcInsEndTime(hi.getEndTime().toString());
                procInsHistoryList.add(ph);
            }
        }
        return procInsHistoryList;
    }
    //查询当前任务
    @GetMapping("/findTask")
    public List<TaskInfo> findMyPersonTask() {
        String assignee = "张三"; // TODO
        List<Task> list = processEngine.getTaskService()// 与正在执行的任务管理相关的service
                .createTaskQuery()// 创建任务查询对象
                // 查询条件
                //.taskAssignee(assignee)// 指定个人任务查询，指定办理人
                // .taskCandidateGroup("")//组任务的办理人查询
                // .processDefinitionId("")//使用流程定义ID查询
                // .processInstanceId("")//使用流程实例ID查询
                // .executionId(executionId)//使用执行对象ID查询
                /** 排序 */
                .orderByTaskCreateTime().asc()// 使用创建时间的升序排列
                // 返回结果集
                // .singleResult() //返回唯一的结果集
                // .count()//返回结果集的数量
                // .listPage(firstResult, maxResults)//分页查询
                .list();// 返回列表
        List<TaskInfo> taskInfoList=new ArrayList<TaskInfo>();

        if (list != null && list.size() > 0) {
            for (Task task : list) {
                TaskInfo taskInfo=new TaskInfo();
                taskInfo.setTaskId(task.getId());
                taskInfo.setTaskName(task.getName());
                taskInfo.setTaskCreateTime(task.getCreateTime().toString());
                taskInfo.setTaskAssignee(task.getAssignee());
                taskInfo.setTaskExecutionId(task.getExecutionId());
                taskInfo.setTaskProcInsId(task.getProcessInstanceId());
                taskInfo.setTaskProcDefId(task.getProcessDefinitionId());
                taskInfoList.add(taskInfo);
            }
        }
        return taskInfoList;
    }
    //完成任务
    @GetMapping("/comTask/{taskId}")
    public String compliteMyPersonTask(@PathVariable("taskId") String taskId) {

        processEngine.getTaskService().complete(taskId);
        return "完成任务：任务ID:" + taskId;
    }
    //查询历史任务
    @GetMapping("/findHisTask/{processInstanceId}")
    public List<TaskHistory> findHistoryTask(@PathVariable("processInstanceId") String processInstanceId){

        List<HistoricTaskInstance> list = processEngine.getHistoryService()//与历史数据（历史表）相关的service
                .createHistoricTaskInstanceQuery()//创建历史任务实例查询
                .processInstanceId(processInstanceId)
                //.taskAssignee(taskAssignee)//指定历史任务的办理人
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
                taskHistory.setTaskUser(hti.getProcessVariables().toString());
                taskHistoryList.add(taskHistory);

            }
        }
        return taskHistoryList;

    }

    //查询流程状态，是否结束或正在执行
    @GetMapping("/procStatus/{processInstanceId}")
    public String isProcessEnd(@PathVariable("processInstanceId") String processInstanceId){
        ProcessInstance pi = processEngine.getRuntimeService()//表示正在执行的流程实例和执行对象
                .createProcessInstanceQuery()//创建流程实例查询
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .singleResult();

        if(pi==null){
            return "流程已经结束";
        }
        else{
            return "流程没有结束";
        }

    }
    //获取流程节点图
    @GetMapping("/procImage/{processInstanceId}")
    public void getProcessImage(@PathVariable("processInstanceId") String processInstanceId, HttpServletResponse response) {
        //log.info("[开始]-获取流程图图像");
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        InputStream imageStream = null;
        try (OutputStream os = response.getOutputStream()){
            //  获取历史流程实例
            HistoricProcessInstance historicProcessInstance = processEngine.getHistoryService()
                    .createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstanceId).singleResult();

            if (historicProcessInstance == null) {
                throw new ServiceException("获取流程实例ID[" + processInstanceId + "]对应的历史流程实例失败！");
            } else {
                // 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
                List<HistoricActivityInstance> historicActivityInstanceList = processEngine.getHistoryService()
                        .createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .orderByHistoricActivityInstanceId()
                        .asc()
                        .list();

                // 已执行的节点ID集合
                List<String> executedActivityIdList = new ArrayList<String>();
                int index = 1;
                //log.info("获取已经执行的节点ID");
                for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                    executedActivityIdList.add(activityInstance.getActivityId());

                   // log.info("第[" + index + "]个已执行节点=" + activityInstance.getActivityId() + " : " +activityInstance.getActivityName());
                    index++;
                }
                // 获取流程定义
                BpmnModel bpmnModel = processEngine.getRepositoryService()
                        .getBpmnModel(historicProcessInstance
                                .getProcessDefinitionId());

                // 已执行的线集合
                List<String> flowIds = getHighLightedFlows(bpmnModel, historicActivityInstanceList);

                // 流程图生成器
                ProcessDiagramGenerator pec = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();
                // 获取流程图图像字符流（png/jpg）
                imageStream = pec.generateDiagram(bpmnModel, "jpg", executedActivityIdList, flowIds, "宋体", "微软雅黑", "黑体", null, 2.0);

                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = imageStream.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }

            }
           // log.info("[完成]-获取流程图图像");
        } catch (Exception e) {
            //log.error("【异常】-获取流程图失败！",e);
        }finally {
            if(imageStream != null){
                try {
                    imageStream.close();
                } catch (IOException e) {
                    //log.error("关闭流异常：",e);
                }
            }
        }
    }

    public List<String> getHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstances) {
        // 24小时制
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 用以保存高亮的线flowId
        List<String> highFlows = new ArrayList<String>();

        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
            // 对历史流程节点进行遍历
            // 得到节点定义的详细信息
            FlowNode activityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(i).getActivityId());

            // 用以保存后续开始时间相同的节点
            List<FlowNode> sameStartTimeNodes = new ArrayList<FlowNode>();
            FlowNode sameActivityImpl1 = null;
            // 第一个节点
            HistoricActivityInstance activityImpl_ = historicActivityInstances.get(i);
            HistoricActivityInstance activityImp2_;

            for (int k = i + 1; k <= historicActivityInstances.size() - 1; k++) {
                // 后续第1个节点
                activityImp2_ = historicActivityInstances.get(k);

                if (activityImpl_.getActivityType().equals("userTask") && activityImp2_.getActivityType().equals("userTask") &&
                        df.format(activityImpl_.getStartTime()).equals(df.format(activityImp2_.getStartTime()))) {
                    // 都是usertask，且主节点与后续节点的开始时间相同，说明不是真实的后继节点
                } else {
                    //找到紧跟在后面的一个节点
                    sameActivityImpl1 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(k).getActivityId());
                    break;
                }

            }
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                // 后续第一个节点
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);
                // 后续第二个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);

                if (df.format(activityImpl1.getStartTime()).equals(df.format(activityImpl2.getStartTime()))) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    FlowNode sameActivityImpl2 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {// 有不相同跳出循环
                    break;
                }
            }
            // 取出节点的所有出去的线
            List<SequenceFlow> pvmTransitions = activityImpl.getOutgoingFlows();
            // 对所有的线进行遍历
            for (SequenceFlow pvmTransition : pvmTransitions) {
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                FlowNode pvmActivityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(pvmTransition.getTargetRef());
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }

        }
        return highFlows;

    }
    //终止流程实例
    @GetMapping("/deleteProc/{processId}")
    public String deleteTaskByProcessId(@PathVariable("processId") String processId){
        processEngine.getRuntimeService().deleteProcessInstance(processId, "结束");
        return "结束流程id："+processId;
    }

}
