<template>
    <div>
        <el-form style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">

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
            <el-form-item label="建议会议时间1" prop="meeting_time1">
                <el-date-picker
                        v-model="ruleForm.meeting_time1"
                        type="datetime"
                        placeholder="选择日期时间"
                        format="yyyy年MM月dd日 HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="建议会议时间2" >
                <el-date-picker
                        v-model="ruleForm.meeting_time2"
                        type="datetime"
                        placeholder="选择日期时间"
                        format="yyyy年MM月dd日 HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="建议会议时间3" >
                <el-date-picker
                        v-model="ruleForm.meeting_time3"
                        type="datetime"
                        placeholder="选择日期时间"
                        format="yyyy年MM月dd日 HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="会议附件" >
                <el-input v-model="ruleForm.document_path"></el-input>
            </el-form-item>


            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>


        <div label="上传附件">  <el-upload
                class="upload-demo"
                ref="upload"
                action="https://jsonplaceholder.typicode.com/posts/"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :file-list="fileList"
                :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
        </div>
    </div>
</template>
<script>
    import axios from  'axios'
    export default {
        data() {
            return {
                fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}],
                ruleForm: {
                    create_name: '',
                    topic:'',
                    place:'',
                    participants:'',
                    meeting_time1:'',
                    meeting_time2:'',
                    meeting_time3:'',
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
                    meeting_time1: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    meeting_time2: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    meeting_time3: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],

                }
            };
        },
        methods: {
            submitForm(formName) {
                const  _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost:8012/meet/save',this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.topic+'会议表创建成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/MeetManagement')
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
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            }
        }
    }
</script>