<template>
    <div>
        <el-form align="center" style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">

        <el-form-item label="申请人姓名" >
            <el-input v-model="ruleForm.name" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="申请类别" >
            <el-input v-model="ruleForm.topic" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="描述" >
            <el-input type="textarea" v-model="ruleForm.description" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="创建时间" >
            <el-date-picker
                    disabled="true"
                    v-model="ruleForm.create_time"
                    type="datetime"
                    placeholder="选择日期时间"
                    format="yyyy年MM月dd日 HH:mm:ss"
                    value-format="yyyy-MM-dd HH:mm:ss">
            </el-date-picker>
        </el-form-item>
        <el-form-item label="修改时间" >
            <el-date-picker
                    disabled="true"
                    v-model="ruleForm.update_time"
                    type="datetime"
                    placeholder="选择日期时间"
                    format="yyyy年MM月dd日 HH:mm:ss"
                    value-format="yyyy-MM-dd HH:mm:ss">
            </el-date-picker>
        </el-form-item>
        <el-form-item label="审核状态">

            <el-switch v-model="ruleForm.review_status"active-color="#13ce66"
                       inactive-color="#ff4949"  active-text="已审"
                       inactive-text="未审" disabled="true"></el-switch>
        </el-form-item>
        <el-form-item label="评审意见" >
            <el-input type="textarea" v-model="ruleForm.review_opinion" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="评审结果" >
            <el-input v-model="ruleForm.review_result" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="评审得票" >
            <el-input v-model="ruleForm.review_vote" disabled="true"></el-input>
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
    export default {
        data() {
            return {
                ruleForm: {
                    name:'',
                    apply_position:'',
                    email:'',
                    time1: '',
                    school1: '',
                    education_level:'',
                    time2: '',
                    school2: '',
                    education_level2:'',
                    create_time:'',
                    document_path:''
                },
                rules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5个字符', trigger: 'blur' }
                    ],
                    apply_position: [
                        { required: true, message: '请输入申请职位', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入联系邮箱', trigger: 'blur' },
                        { min: 5, max: 50, message: '长度在 5 到 50 个字符', trigger: 'blur' }
                    ],
                    time1: [
                        { required: true, message: '请填写就读时间', trigger: 'blur' },
                        { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
                    ],
                    school1: [
                        { required: true, message: '请填写就读学校', trigger: 'blur' },
                        { min: 4, max: 30, message: '长度在4 到 30 个字符', trigger: 'blur' }
                    ],
                    education_level: [
                        { required: true, message: '请填写就读时间', trigger: 'blur' },
                        { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
                    ],
                    create_time: [
                        {  required: true, message: '请选择时间', trigger: 'change' }
                    ]


                }
            }
        },
        methods: {
            submitForm(formName) {
                const _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url='http://localhost:8015/apply/update';
                        _this.axios.put(url,this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.name + '申请修改成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/ApplyManagement')
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
            let url='http://localhost:8015/apply/findById/'+ this.$route.query.id;
            _this.axios.get(url).then(function(resp) {
                _this.ruleForm=resp.data
            })

        }
    }
</script>

<style scoped>

</style>
