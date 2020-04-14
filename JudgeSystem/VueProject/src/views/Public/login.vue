<template>
    <div>
        <!--flex弹性盒子模型，justify-content：主抽 -->
        <div style="display: flex;justify-content: center;margin-top: 150px" >
            <el-card style="width: 400px" >
                <div slot="header" class="clearfix" align="center">
                    <span>评审系统登录</span>
                </div>
                <el-form  style="width: 100%"  :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item  label="用户账号" prop="username">
                        <el-input prefix-icon="el-icon-message" v-model="ruleForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="用户密码" prop="password">
                        <el-input prefix-icon="el-icon-lock" v-model="ruleForm.password" show-password></el-input>
                    </el-form-item>
                    <el-form-item label="登录方式" prop="role">
                        <el-select style="width: 45%"  v-model="ruleForm.role" placeholder="请选择方式">
                            <el-option label="校内人员" value="user"></el-option>
                            <el-option label="校外人员" value="applicant"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                        <el-button type="primary" @click="SignIn('ruleForm')">注册</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-card>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
                    username: '',
                    password: '',
                    role:''
                },
                user: [],
                rules: {
                    username: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        { min: 3, max:20, message: '长度在 3 到20 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 3, max:20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
                    ],
                    role: [
                        { required: true, message: '请选择活动区域', trigger: 'change' }
                    ]

                }
            };
        },
        methods: {
            submitForm(formName) {
                const  _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        _this.axios.get('http://localhost:8010/account/login/'+this.ruleForm.username+'/'+this.ruleForm.password+'/'+this.ruleForm.role).then(function (resp) {
                            console.log(resp)
                            _this.token=resp.data
                            if(_this.token.name!=null){
                            _this.$alert(_this.token.name+'登录成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$store.commit('login', _this.token)
                                    _this.$router.push('/')

                                }
                            });
                            }else{
                                alert("登录失败")

                            }
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
            SignIn(formName){
                const  _this=this
                _this.$router.push('/SignIn')

            }
        }
    }
</script>


<style scoped>

</style>