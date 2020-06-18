/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.judgesystem.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.judgesystem.entity.NewModel;
import com.judgesystem.page.PageInfo;
import com.judgesystem.page.PageUtil;
import com.judgesystem.respnse.RespData;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ModelEntityImpl;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * 模型管理类
 *
 * @author dgb
 */
@Slf4j
@RestController
@RequestMapping("/model")
public class ModelController {

    private final String MODEL_ID = "modelId";
    private final String MODEL_NAME = "name";
    private final String MODEL_DESCRIPTION = "description";
    private final String MODEL_REVISION = "revision";

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RuntimeService runtimeService;


    /**
     * 获取所有模型
     *
     * @return
     */
   /* @PostMapping("/findAllModel")
    public RespData<PageInfo<Model>> search(@RequestBody ModelEntityImpl model, Integer pageNum, Integer pageSize) {
        ModelQuery modelQuery = repositoryService.createModelQuery();
        if (StringUtils.isNotBlank(model.getName())) {
            modelQuery.modelNameLike("%" + model.getName() + "%");
        }
        if (StringUtils.isNotBlank(model.getKey())) {
            modelQuery.modelKey(model.getKey());
        }
        modelQuery.orderByCreateTime().desc();
        long total = modelQuery.count();
        List<Model> models = modelQuery.listPage(pageNum - 1, pageSize);
        PageInfo<Model> pageInfo = PageUtil.toPageInfo(models);
        pageInfo.setTotal(total);
        return RespData.ok(pageInfo);
    }*/
   @GetMapping("/findAll")
   public List<Model> findAllModel(){
       List<Model> resultList=repositoryService.createModelQuery().orderByCreateTime().desc().list();

       List<ProcessDefinition> list=repositoryService
               .createProcessDefinitionQuery()
               .orderByProcessDefinitionVersion().asc()
               .list();
       /*JSONObject resultJson=new JSONObject();
       resultJson.put("data",resultList);*/
       return resultList;
   }
   @GetMapping("/sus/{key}")
   public void suspendProcessDef(@PathVariable("key") String key){
       repositoryService.suspendProcessDefinitionByKey(key);
   }

    /**
     * 保存模型
     *
     * @param newModel
     * @return
     */
    @PostMapping("/save")
    public RespData<String> create(@RequestBody NewModel newModel) {

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(MODEL_NAME, newModel.getName());
        modelNode.put(MODEL_DESCRIPTION, newModel.getDesc());
        modelNode.put(MODEL_REVISION, "1");

        Model model = repositoryService.newModel();
        model.setName(newModel.getName());
        model.setKey(newModel.getKey());
        model.setCategory(newModel.getCategory());
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        String id = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace",
                "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.putPOJO("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id, editorNode.toString().getBytes(StandardCharsets.UTF_8));
        return RespData.ok(id);
    }

    /**
     * 根据Id查询模型
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public NewModel getById(@PathVariable("id") String id) {
        Model model = repositoryService.createModelQuery().modelId(id).singleResult();
        NewModel newModel=new NewModel();
        newModel.setId(model.getId());
        newModel.setKey(model.getKey());
        newModel.setName(model.getName());
        newModel.setDesc(model.getMetaInfo());
        newModel.setCategory(model.getCategory());
        //return RespData.ok(model);
        return newModel;
    }

    /**
     * 删除模型
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public RespData<?> delete(@PathVariable("id") String id) {
        Model model = repositoryService.createModelQuery().modelId(id).singleResult();
        if (model == null) {
            return RespData.failed().appendMsg("模型不存在").build();
        }
        repositoryService.deleteModel(id);
        repositoryService.deleteDeployment(model.getDeploymentId(),true);
        return RespData.sucess().build();
    }

    /**
     * 获取流程定义json数据
     *
     * @param modelId
     * @return
     */
    @GetMapping("/{modelId}/json")
    public ObjectNode getEditorJson(@PathVariable String modelId) {
        ObjectNode modelNode = null;

        Model model = repositoryService.getModel(modelId);

        if (model != null) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = objectMapper.createObjectNode();
                    modelNode.put(MODEL_NAME, model.getName());
                }
                modelNode.put(MODEL_ID, model.getId());
                byte[] modelEditorSource = repositoryService.getModelEditorSource(model.getId());
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(new String(modelEditorSource, StandardCharsets.UTF_8));
                modelNode.putPOJO("model", editorJsonNode);

            } catch (Exception e) {
                log.error("Error creating model JSON", e);
                throw new ActivitiException("Error creating model JSON", e);
            }
        }
        return modelNode;
    }

    /**
     * 保存流程定义数据
     */
    @PutMapping("/{modelId}/save")
    public void saveModel(@PathVariable String modelId, @RequestParam("name") String name,
                          @RequestParam("json_xml") String json_xml,
                          @RequestParam("svg_xml") String svg_xml,
                          @RequestParam("description") String description) {
        try {

            Model model = repositoryService.getModel(modelId);

            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());

            modelJson.put(MODEL_NAME, name);
            modelJson.put(MODEL_DESCRIPTION, description);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);
            model.setVersion(model.getVersion() + 1);
            repositoryService.saveModel(model);

            repositoryService.addModelEditorSource(model.getId(), Objects.requireNonNull(json_xml.getBytes(StandardCharsets.UTF_8)));

            InputStream svgStream = new ByteArrayInputStream(Objects.requireNonNull(svg_xml.getBytes(StandardCharsets.UTF_8)));
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            // Do the transformation
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();

        } catch (Exception e) {
            log.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        }
    }

    /**
     * 部署模型
     *
     * @param modelId
     * @return
     */
    @GetMapping("/{modelId}/deployment")
    public RespData<?> deploy(@PathVariable("modelId") String modelId) {

        // 获取模型
        Model modelData = repositoryService.getModel(modelId);

        if (modelData == null) {
            return RespData.invalid().appendMsg("模型不存在").build();
        }

        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return RespData.invalid().appendMsg("请先设计流程定义并成功保存，再进行部署").build();
        }

        JsonNode modelNode = null;
        try {
            modelNode = new ObjectMapper().readTree(bytes);
            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            if (model.getProcesses().size() == 0) {
                return RespData.invalid().appendMsg("流程定义不符要求，请至少设计一条主线流程").build();
            }
            byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
            //发布流程
            String processName = modelData.getKey() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment()
                    .name(modelData.getName())
                    .key(modelData.getKey())
                    .category(modelData.getCategory())
                    .addString(processName, new String(bpmnBytes, StandardCharsets.UTF_8))
                    .enableDuplicateFiltering() // 在部署时会检测已部署的相同文件的最后一条记录，如果内容相同，则不会部署
                    .deploy();
            modelData.setDeploymentId(deployment.getId());
            repositoryService.saveModel(modelData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RespData.sucess().build();
    }
}
