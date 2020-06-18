<template>
    <div>

            <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                <el-form-item label="通知主题" prop="position">
                    <el-input v-model="ruleForm.notifi_name"></el-input>
                </el-form-item>
                <el-form-item label="通知描述" prop="number">
                    <el-input type="textarea" v-model="ruleForm.notifi_desc"></el-input>
                </el-form-item>
                <el-form-item label="开始时间" prop="start_time">
                    <el-date-picker
                            v-model="ruleForm.start_time"
                            type="date"
                            placeholder="选择日期时间"
                            format="yyyy 年 MM 月 dd 日"
                            value-format="yyyy-MM-dd">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="截止时间" prop="end_time">
                    <el-date-picker
                            v-model="ruleForm.end_time"
                            type="date"
                            placeholder="选择日期时间"
                            format="yyyy 年 MM 月 dd 日"
                            value-format="yyyy-MM-dd">
                    </el-date-picker>
                </el-form-item>
                <!-- <el-form-item label="创建时间" prop="create_time">
                     <el-date-picker
                             v-model="ruleForm.create_time"
                             type="datetime"
                             placeholder="选择日期时间"
                             format="yyyy年MM月dd日 HH:mm:ss"
                             value-format="yyyy-MM-dd HH:mm:ss">
                     </el-date-picker>
                 </el-form-item>-->

                <el-form-item label="创建人" prop="create_user">
                    <el-input v-model="ruleForm.create_user"></el-input>
                </el-form-item>
                <el-form-item label="文件路径" >
                    <el-input v-model="ruleForm.document"></el-input>
                </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>


        <div label="上传附件">  <el-upload
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
        </div>
    </div>
</template>
<script>
    import axios from  'axios'
    export default {
        data() {
            return {
                files: [],
                ruleForm: {
                    id:'',
                    notifi_name:'',
                    notifi_desc:'',
                    start_time:'',
                    end_time:'',
                    create_time:'',
                    create_user:'',
                    del_sign:'',
                    document:'',

                },
                rules: {
                    notifi_name: [
                        { required: true, message: '请输入通知名', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    notifi_desc: [
                        { required: true, message: '请输入招聘人数', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],

                    start_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    end_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],

                    create_user: [
                        { required: true, message: '请输入联系人', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],


                },
            };
        },
        methods: {
            submitForm(formName) {
                const  _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(_this.ruleForm)
                        let url='http://localhost:8014/notify/save';
                        _this.axios.post(url,this.ruleForm).then(function (resp) {

                                _this.$alert(_this.ruleForm.notifi_name+'发布成功', 'OK', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/TaskManage')
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
