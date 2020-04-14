<template>
    <div>
        <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">

            <el-form-item label="招聘单位" prop="department">
                <el-select v-model="ruleForm.department" placeholder="请选择招聘单位">
                    <el-option label="计算机信息与技术学院 软件学院" value="计算机信息与技术学院 软件学院"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="招聘岗位" prop="position">
                <el-input v-model="ruleForm.position"></el-input>
            </el-form-item>
            <el-form-item label="招聘人数" prop="number">
                <el-input v-model="ruleForm.number"></el-input>
            </el-form-item>
            <el-form-item label="工作待遇" prop="treatment">
                <el-input v-model="ruleForm.treatment"></el-input>
            </el-form-item>
            <el-form-item label="招聘开始时间" prop="start_time">
                <el-date-picker
                        v-model="ruleForm.start_time"
                        type="date"
                        placeholder="选择日期时间"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="招聘截止时间" prop="end_time">
                <el-date-picker
                        v-model="ruleForm.end_time"
                        type="date"
                        placeholder="选择日期时间"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="创建时间" prop="create_time">
                <el-date-picker
                        v-model="ruleForm.create_time"
                        type="datetime"
                        placeholder="选择日期时间"
                        format="yyyy年MM月dd日 HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="招聘要求" prop="requirement">
                <el-input type="textarea" v-model="ruleForm.requirement"></el-input>
            </el-form-item>
            <el-form-item label="创建人" prop="create_name">
                <el-input v-model="ruleForm.create_name"></el-input>
            </el-form-item>
            <el-form-item label="联系邮箱" prop="email">
                <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
            <el-form-item label="文件路径" prop="document_path">
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
                    department: '',
                    position:'',
                    number:'',
                    requirement:'',
                    treatment:'',
                    start_time: '',
                    end_time:'',
                    create_time: '',
                    create_name: '',
                    email: '',
                    document_path:''
                },
                rules: {
                    position: [
                        { required: true, message: '请输入招聘职位', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    number: [
                        { required: true, message: '请输入招聘人数', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    treatment: [
                        { required: true, message: '请输入工作待遇', trigger: 'blur' },
                        { min: 1, max: 200, message: '长度在 1 到 200 个字符', trigger: 'blur' }
                    ],
                    department: [
                        { required: true, message: '请选择招聘单位', trigger: 'blur' }
                    ],
                    start_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    end_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    create_time: [
                        {  required: true, message: '请选择时间', trigger: 'change' }
                    ],
                    create_name: [
                        { required: true, message: '请输入联系人', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入联系邮箱', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                    ],

                    requirement: [
                        { required: true, message: '请填写招聘需求', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8014/job/update',this.ruleForm).then(function (resp) {
                                _this.$alert(_this.ruleForm.position + '岗位招聘修改成功', 'OK', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/JobManagement')
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
            axios.get('http://localhost:8014/job/findById/'+ this.$route.query.id).then(function(resp) {
                _this.ruleForm=resp.data
            })

        }
    }
</script>

<style scoped>

</style>