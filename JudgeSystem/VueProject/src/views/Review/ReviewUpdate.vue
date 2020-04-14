<template>
    <div>
        <el-form style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">

            <el-form-item label="申请人" prop="applyer">
                <el-input v-model="ruleForm.applyer"></el-input>
            </el-form-item>
            <el-form-item label="申请岗位" prop="apply_position">
                <el-input v-model="ruleForm.apply_position"></el-input>
            </el-form-item>
            <el-form-item label="评审状态" prop="review_status">
                <el-input v-model="ruleForm.review_status"></el-input>
            </el-form-item>
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
            <el-form-item label="评审意见" >
                <el-input type="textarea" v-model="ruleForm.review_opinion"></el-input>
            </el-form-item>
            <el-form-item label="评审结果" >
                <el-input type="textarea" v-model="ruleForm.review_result"></el-input>
            </el-form-item>
            <el-form-item label="评审创建时间" prop="create_time">
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
                    applyer: '',
                    apply_position:'',
                    review_status:'',
                    start_time:'',
                    end_time:'',
                    review_opinion:'',
                    review_result:'',
                    create_time: '',
                    document_path:''
                },
                rules: {
                    applyer: [
                        { required: true, message: '请输入申请人姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5个字符', trigger: 'blur' }
                    ],
                    apply_position: [
                        { required: true, message: '请输入申请职位', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    review_status: [
                        { required: true, message: '请输入评审状态', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                    ],
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
                const _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8013/judge/update',this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.applyer + '评审表修改成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/ReviewManagement')
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
            axios.get('http://localhost:8013/judge/findById/'+ this.$route.query.id).then(function(resp) {
                _this.ruleForm=resp.data
            })

        }
    }
</script>

<style scoped>

</style>