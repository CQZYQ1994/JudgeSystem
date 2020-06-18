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
            <el-form-item label="会议状态">
                <el-switch v-model="ruleForm.meet_status"active-color="#13ce66"
                           inactive-color="#ff4949"  active-text="启用"
                           inactive-text="过期"></el-switch>
            </el-form-item>
            <el-form-item label="会议创建时间" prop="create_time">
                <el-date-picker
                        v-model="ruleForm.create_time"
                        type="datetime"
                        placeholder="选择日期时间"
                        format="yyyy年MM月dd日 HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss">
                </el-date-picker>
            </el-form-item>
           <!-- <el-form-item label="建议会议时间2" >
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
            </el-form-item>-->


            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>


      <!--  <div label="上传附件">  <el-upload
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
        </el-upload>-->
       <!-- </div>-->
    </div>
</template>
<script>
    import axios from  'axios'
    export default {
        data() {
            return {

                ruleForm: {
                    create_name: '',
                    topic:'',
                    place:'',
                    participants:'',
                    meet_status:'',
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

                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url='http://localhost:8012/meetInfo/update';
                        _this.axios.put(url,this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.topic + '会议表修改成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/MeetManage')
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
            }
        },
        created() {
            const _this=this
            let url='http://localhost:8012/meetInfo/findById/'+ this.$route.query.id;
            _this.axios.get(url).then(function(resp) {
                _this.ruleForm=resp.data
            })

        }
    }
</script>

<style scoped>

</style>
