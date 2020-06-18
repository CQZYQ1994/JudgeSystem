<template>
  <div>  <el-form  style="width:60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="220px" class="demo-ruleForm">

        <el-form-item label="申请人姓名" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="主题" prop="topic">
            <el-input v-model="ruleForm.topic"></el-input>
        </el-form-item>
      <el-form-item label="申请流程类型" prop="processDefKey">
          <el-select v-model="ruleForm.processDefKey" placeholder="请选择流程">
              <el-option label="工作申请流程" value="jobApplyReviewProcess"></el-option>
              <el-option label="请假流程" value="leaveprocess"></el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="描述" prop="description">
          <el-input type="textarea" v-model="ruleForm.description"></el-input>
      </el-form-item>
    <!--  <el-form-item label="创建时间" prop="create_time">
          <el-date-picker
                  v-model="ruleForm.create_time"
                  type="datetime"
                  placeholder="选择日期时间"
                  format="yyyy年MM月dd日 HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间" prop="update_time">
          <el-date-picker
                  v-model="ruleForm.update_time"
                  type="datetime"
                  placeholder="选择日期时间"
                  format="yyyy年MM月dd日 HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
      </el-form-item>-->
      <el-form-item>
          <el-upload
                  class="upload-demo"
                  ref="upload"
                  action="http://localhost:8018/file/uploadFiles"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :file-list="files"
                  :auto-upload="false"
                  :before-upload="beforeUploadFile">
              <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
              <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
              <div slot="tip" class="el-upload__tip">上传文件不宜超过200M</div>
          </el-upload>
      </el-form-item>
        <el-form-item label="输入文件名" prop="document">
            <el-input v-model="ruleForm.document"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
    </el-form>
      <div label="上传附件">
      </div>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
                    id:'',
                    name:'',
                    topic:'',
                    description:'',
                    review_status:false,
                    create_time:'',
                    update_time:'',
                    review_opinion:'',
                    review_result:'',
                    review_vote:0,
                    document:'',
                    processDefKey:'',
                    processInsId:'',
                    user_id:JSON.parse(window.localStorage.getItem('token' || '[]')).id,
                    userInfo:{
                        username: '',
                        password: '',
                        name: '',
                        postId:'',
                        gender:'',
                        birthday:'',
                        nation:'',
                        nativePlace:'',
                        email: '',
                        department: '',
                        position: '',
            }
                },
                rules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5个字符', trigger: 'blur' }
                    ],
                    topic: [
                        { required: true, message: '请输入申请类别', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    description: [
                        { required: true, message: '请输入申请描述', trigger: 'blur' },
                        { min: 5, max: 50, message: '长度在 5 到 200 个字符', trigger: 'blur' }
                    ],
                    document: [
                        { required: true, message: '请输入上传文件名', trigger: 'blur' },
                        { min: 1,  message: '至少一个字符', trigger: 'blur' }
                    ],
                    create_time: [
                        {  required: true, message: '请选择时间', trigger: 'change' }
                    ],
                    processDefKey: [
                        { required: true, message: '请选择', trigger: 'change' }
                    ],
                    update_time: [
                 {  required: true, message: '请选择时间', trigger: 'change' }
             ]


                },
                files:[]
            }
        },
        methods: {
            submitForm(formName) {
                const  _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url='http://localhost:8015/apply/insert';
                        _this.axios.post(url,this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.topic+'创建成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/ApplyInfo')
                                    /* console.log(resp)*/
                                }
                            });

                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file, files) {
                console.log(file, files);
            },
            handlePreview(file) {
                console.log(file);
            },
            beforeUploadFile(file){
                const isLt2M=file.size/1024/1024<200;
                if(!isLt2M){
                    this.$message.error('文件不能超过200M')
                }
                return isLt2M;
            }
        }
    }
</script>
