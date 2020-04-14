<template>



    <div>
        <!--flex弹性盒子模型，justify-content：主抽 -->
        <div style="display: flex;justify-content: center;margin-top: 10px">
            <el-card style="width: 400px">
                <div slot="header" class="clearfix" align="center">
                    <span>邮件发送</span>
                </div>
                <el-form style="width: 100%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="发送人" prop="from">
                        <el-input v-model="ruleForm.from"></el-input>
                    </el-form-item>
                    <el-form-item label="收件人" prop="to">
                        <el-input v-model="ruleForm.to"></el-input>
                    </el-form-item>
                    <el-form-item label="抄送人" prop="cc">
                        <el-input v-model="ruleForm.cc" ></el-input>
                    </el-form-item>
                    <el-form-item label="邮件主题" prop="subject">
                        <el-input v-model="ruleForm.subject"></el-input>
                    </el-form-item>
                    <el-form-item label="邮件内容" prop="content">
                        <el-input type="textarea" v-model="ruleForm.content"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">发送</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                        <el-button @click="CallBack('ruleForm')">返回</el-button>

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
            </el-card>

        </div>
    </div>
</template>
<script>
    import axios from  'axios'
    export default {
        data() {
            return {
                ruleForm: {
                    from: '',
                    to: '',
                    cc: '',
                    subject: '',
                    content: '',
                    file:''

                },
                rules: {

                    from: [
                        { required: true, message: '请输入发送者邮箱', trigger: 'blur' },
                        { min: 3, max: 50, message: '长度在50个字符', trigger: 'blur' }
                    ],
                    to: [
                        { required: true, message: '请输入收件者邮箱', trigger: 'blur' },
                        { min: 3, max: 50, message: '长度在3到50个字符', trigger: 'blur' }
                    ],
                    cc: [
                        { required: true, message: '请输入邮件抄送者', trigger: 'blur' },
                        { min: 3, max: 50, message: '长度在3到50个字符', trigger: 'blur' }
                    ],
                    subject: [
                        { required: true, message: '请输入邮件主题', trigger: 'blur' },
                        { min: 3, max: 50, message: '长度在3到50个字符', trigger: 'blur' }
                    ],
                    content: [
                        { required: true, message: '请输入邮件内容', trigger: 'blur' },
                        { min: 3, max: 500, message: '长度在3到500个字符', trigger: 'blur' }
                    ],



                }
            };
        },
        methods: {
            submitForm(formName) {
                const  _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost:8016/mail/send'+this.ruleForm).then(function (resp) {

                                _this.$alert(_this.ruleForm.subject+'发送成功', 'OK', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/SearchUser')
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
            CallBack(formName){
                const  _this=this
                _this.$router.push('/login')
            }
        }
    }
</script>