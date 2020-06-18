<template>
    <div align="center">
        <el-form style="width: 40%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户姓名" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="职工号" prop="postId">
                <el-input v-model="ruleForm.postId"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="ruleForm.gender">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="登录账号" prop="username">
                <el-input v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-form-item label="用户密码" prop="password">
                <el-input v-model="ruleForm.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="出生日期" prop="birthday">
                <el-date-picker
                        v-model="ruleForm.birthday"
                        type="date"
                        placeholder="选择日期时间"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="民族" prop="nation">
                <el-input v-model="ruleForm.nation" ></el-input>
            </el-form-item>
            <el-form-item label="籍贯" prop="nativePlace">
                <el-input v-model="ruleForm.nativePlace" ></el-input>
            </el-form-item>
            <el-form-item label="用户邮箱" prop="email">
                <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
            <el-form-item label="单位" prop="department">
                <el-select v-model="ruleForm.department" placeholder="请选择所在单位">
                    <el-option label="西南大学计算机与信息科学学院" value="西南大学计算机与信息科学学院"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="职位" prop="position">
                <el-select v-model="ruleForm.position" placeholder="请选择所任职位">
                    <el-option label="教授" value="教授"></el-option>
                    <el-option label="副教授" value="副教授"></el-option>
                    <el-option label="讲师" value="讲师"></el-option>
                    <el-option label="教学秘书" value="教学秘书"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="角色权限" prop="role">
                <el-select v-model="ruleForm.role.id" placeholder="请选择角色权限">
                    <el-option label="超级管理员" value="1"></el-option>
                    <el-option label="教学秘书" value="2"></el-option>
                    <el-option label="评审负责人" value="3"></el-option>
                    <el-option label="评审老师" value="4"></el-option>
                    <el-option label="申请人" value="5"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>

            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
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
                    role:{
                        id:'',
                        role:'',
                        role_name:''
                    }
                },
                rules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在2到5个字符', trigger: 'blur' }
                    ],
                    postId: [
                        { required: true, message: '请输入职工号', trigger: 'blur' },
                        { min: 2, max:20, message: '长度在2到20个字符', trigger: 'blur' }
                    ],
                    gender: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                    username: [
                        { required: true, message: '请输入登录账号', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在3到20个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入用户密码', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在3到20个字符', trigger: 'blur' }
                    ],
                    nation: [
                        { required: true, message: '请输入民族', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在1到10个字符', trigger: 'blur' }
                    ],
                    birthday: [
                        {  required: true, message: '请输入出生日期', trigger: 'change' }
                    ],
                    nativePlace: [
                        { required: true, message: '请输入籍贯', trigger: 'blur' },
                        { min: 2, max: 20, message: '长度在2到20个字符', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入用户邮箱', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在3到20个字符', trigger: 'blur' }
                    ],
                    department: [
                        {  required: true, message: '请至少选择一个部门', trigger: 'blur' }
                    ],
                    position: [
                        {  required: true, message: '请至少选择一个职位', trigger: 'blur' }
                    ],
                    role: [
                        { required: true, message: '请至少选择一个角色', trigger: 'blur' }
                    ]

                }
            };
        },
        methods: {
            submitForm(formName) {
                const  _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url='http://localhost:8010/user/update';
                        _this.axios.put(url,this.ruleForm).then(function (resp) {
                            console.log(resp)
                            if (resp.status===200) {
                                _this.$alert(_this.ruleForm.name + '修改成功', 'OK', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/UserManage')
                                    }
                                });
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
            }
        },
        created() {
            const _this=this
            let url='http://localhost:8010/user/findById/'+ this.$route.query.id;
            _this.axios.get(url).then(function(resp) {
                    if(resp.status===200){_this.ruleForm=resp.data}
            })

        }
    }
</script>

<style scoped>

</style>
