<template>
    <div>
        <el-form style="width:60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="160px" class="demo-ruleForm">

            <el-form-item label="会议创建人" prop="create_name">
                <el-input v-model="ruleForm.create_name"></el-input>
            </el-form-item>
            <el-form-item label="会议议题" prop="topic">
                <el-input v-model="ruleForm.topic"></el-input>
            </el-form-item>
            <el-form-item label="会议地点" prop="place">
                <el-input v-model="ruleForm.place"></el-input>
            </el-form-item>
            <el-form-item label="与会人员" prop="participants">
                <el-input v-model="ruleForm.participants"></el-input>
            </el-form-item>
            <el-form-item label="会议任务创建时间" prop="create_time">
                <el-date-picker
                        v-model="ruleForm.create_time"
                        type="datetime"
                        placeholder="选择日期时间"
                        format="yyyy年MM月dd日 HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="文件名" prop="document_path">
                <el-input v-model="ruleForm.document_path"></el-input>
            </el-form-item>
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
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="goback">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import axios from  'axios'
    export default {
        data() {
            return {
                files: [],
                ruleForm: {
                    create_name: '',
                    topic:'',
                    place:'',
                    meet_status:true,
                    participants:'',
                    create_time:'',
                    document_path:''
                },
                rules: {
                    create_name: [
                        { required: true, message: '请输入创建人姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5个字符', trigger: 'blur' }
                    ],
                    topic: [
                        { required: true, message: '请输入会议议题', trigger: 'blur' },
                        { min: 2, max:50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
                    ],
                    place: [
                        { required: true, message: '请输入会议地点', trigger: 'blur' },
                        { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
                    ],
                    participants: [
                        { required: true, message: '请输入与会人员', trigger: 'blur' },
                        { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
                    ],
                    create_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    meeting_time2: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    meeting_time3: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    document_path: [
                        { required: true, message: '请输入上传文件名', trigger: 'blur' },
                        { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
                    ],

                }
            };
        },
        methods: {
            submitForm(formName) {
                const  _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url='http://localhost:8012/meetInfo/save';
                        _this.axios.post(url,_this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.topic+'会议表创建成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/MeetManage')
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
            goback() {
                this.$router.push('/MeetManage');
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
