<template>
    <div>
    <div  class="row" id="pdfDom" style="padding-top: 25px;background-color:#fff;">
        <el-form style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">

            <el-form-item label="申请人">
                <el-input v-model="ruleForm.name"  disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="申请类别">
                <el-input v-model="ruleForm.topic"  disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="描述">
                <el-input type="textarea" v-model="ruleForm.description"  disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="评审状态" prop="review_status">
                <el-switch v-model="ruleForm.review_status"></el-switch>
            </el-form-item>

            <el-form-item label="评审意见" prop="review_opinion">
                <el-input type="textarea" v-model="ruleForm.review_opinion"></el-input>
            </el-form-item>
            <el-form-item label="评审结果" prop="review_result">
                <el-select v-model="ruleForm.review_result" placeholder="请选择">
                    <el-option label="通过" value="通过"></el-option>
                    <el-option label="不通过" value="不通过"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="getBack">回退</el-button>
            </el-form-item>
        </el-form>
    </div>
  </div>
</template>
<script>
    import axios from  'axios'
    export default {
        data() {
            return {
                ruleForm: {
                    id:'',
                    name:'',
                    topic:'',
                    description:'',
                    review_status:'',
                    create_time:'',
                    update_time:'',
                    review_opinion:'',
                    review_result:'',
                    review_vote:0,
                    document:'',
                    user_id:'',
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
                    review_opinion: [
                        { required: true, message: '请输入评审意见', trigger: 'blur' },
                        { min: 1, max: 300, message: '长度在 1 到 300 个字符', trigger: 'blur' }
                    ],
                    review_result: [
                         { required: true, message: '请选择评审结果', trigger: 'change' }
                     ],
                    update_time: [
                        {  required: true, message: '请选择时间', trigger: 'change' }
                    ],
                },
                htmlTitle:'导出PDF'
            };
        },
        methods: {
            submitForm(formName) {
                const _this=this
                _this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url='http://localhost:8015/apply/update';
                        _this.axios.put(url,this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.name + '评审成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/ReviewApply')
                                }
                            });
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            getBack(){
                this.$router.push('/ReviewApply')
            }
        },
        created() {
            const _this=this
            let url='http://localhost:8015/apply/findById/'+ this.$route.query.id
            _this.axios.get(url).then(function(resp) {
                _this.ruleForm=resp.data
            })

        }
    }
</script>

<style scoped>

</style>
