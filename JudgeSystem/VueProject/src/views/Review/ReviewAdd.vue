<template>
    <div>
        <el-form style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">

            <el-form-item label="评审对象" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="评审职位" prop="position">
                <el-input v-model="ruleForm.position"></el-input>
            </el-form-item>
           <!-- <el-form-item label="评审状态" prop="review_status">
                <el-input v-model="ruleForm.review_status"></el-input>
            </el-form-item>-->
            <el-form-item label="评审开始时间" prop="start_time">
                <el-date-picker
                        v-model="ruleForm.start_time"
                        type="date"
                        placeholder="选择日期时间"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="评审截止时间" prop="end_time">
                <el-date-picker
                        v-model="ruleForm.end_time"
                        type="date"
                        placeholder="选择日期时间"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
           <!-- <el-form-item label="评审意见" >
                <el-input type="textarea" v-model="ruleForm.review_opinion"></el-input>
            </el-form-item>
            <el-form-item label="评审结果" >
                <el-input type="textarea" v-model="ruleForm.review_result"></el-input>
            </el-form-item>-->
            <el-form-item label="评审创建时间" prop="create_time" class="block">
                <el-date-picker
                        v-model="ruleForm.create_time"
                        type="datetime"
                        placeholder="选择日期时间"
                        format="yyyy年MM月dd日 HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="评审附件" >
                <el-input v-model="ruleForm.document_path"></el-input>
            </el-form-item>


            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>


        <div label="上传附件"> <el-upload
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
            <div slot="tip" class="el-upload__tip">上传文件不宜超过20M</div>
        </el-upload>
        </div>
    </div>
</template>
<script>
    import axios from  'axios'
    export default {
        data() {
            return {
                files:[],
                ruleForm: {
                    name: '',
                    position:'',
                    review_status:false,
                    start_time:'',
                    end_time:'',
                    review_opinion:'',
                    review_result:0,
                    create_time: '',
                    document_path:''
                },
                rules: {
                    name: [
                        { required: true, message: '请输入申请人姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5个字符', trigger: 'blur' }
                    ],
                    position: [
                        { required: true, message: '请输入申请职位', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                /*    review_status: [
                        { required: true, message: '请输入评审状态', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                    ],*/
                    start_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    end_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    review_opinion: [
                        { required: true, message: '请输入评审意见', trigger: 'blur' },
                        { min: 1, max: 300, message: '长度在 1 到 300 个字符', trigger: 'blur' }
                    ],
                    review_result: [
                        { required: true, message: '请输入评审意见', trigger: 'blur' },
                        { min: 1, max: 300, message: '长度在 1 到 300 个字符', trigger: 'blur' }
                    ],
                    create_time: [
                        {  required: true, message: '请选择时间', trigger: 'change' }
                    ],
                    document_path: [
                        { required: true, message: '请上传附件', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const  _this=this
                console.log(_this.ruleForm)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                       _this.axios.post('http://localhost:8013/judge/save',_this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.name+'评审表创建成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/ReviewManagement')
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
                const isLt2M=file.size/1024/1024<20;
                if(!isLt2M){
                    this.$message.error('文件不能超过20M')
                }
                return isLt2M;
            }
        }
    }
</script>