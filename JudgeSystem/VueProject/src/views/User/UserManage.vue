<template>
    <div>

        <div>
            <div style="display: flex;justify-content: space-between">
            <div>
                <el-input placeholder="请输入员工名，回车搜索..." prefix-icon="el-icon-search"
                          clearable
                          @clear="initUser"
                          style="width: 350px;margin-right: 10px" v-model="keyword"
                          @keydown.enter.native="initUser" ></el-input>
               <!-- <el-button icon="el-icon-search" type="primary" @click="initUser" :disabled="showAdvanceSearchView">
                    搜索
                </el-button>-->
            </div><div>
                    <el-select v-model="role" @change="selectByRole(role)">
                        <el-option label="普通用户" value="applicant"></el-option>
                        <el-option label="学术委员" value="reviewer"></el-option>
                        <el-option label="学术秘书" value="assistant"></el-option>
                        <el-option label="管理员" value="admin"></el-option>
                        <el-option label="全部" value="findAll"></el-option>
                    </el-select>

            </div>
            <div>
                <el-upload
                        :show-file-list="false"
                        :before-upload="beforeUpload"
                        :on-success="onSuccess"
                        :on-error="onError"
                        :disabled="importDataDisabled"
                        :file-list="files"
                        class="upload-demo"
                        ref="upload"
                        style="display: inline-flex;margin-right: 8px"
                        action="http://localhost:8010/excl/import">
                    <el-button :disabled="importDataDisabled" type="success" :icon="importDataBtnIcon" @click="submitUpload">
                        {{importDataBtnText}}
                    </el-button>
                </el-upload>
                <el-button type="success" @click="exportData" icon="el-icon-download" download="11">
                    导出数据
                </el-button>
                <el-button type="primary" icon="el-icon-plus" @click="sendToAddUser">
                    添加用户
                </el-button>

            </div>
        </div>
            <br>
            <el-dialog title="查询结果" :visible.sync="dialogFormVisible">
                <el-table :data="user">
                    <el-table-column prop="name" label="姓名" width="50"></el-table-column>
                    <el-table-column prop="gender" label="性别" width="50"></el-table-column>
                    <el-table-column prop="nation" label="民族" width="50"></el-table-column>
                </el-table>
            </el-dialog>
        </div>
        <div>
            <el-dialog title="修改用户" :visible.sync="dialogFormVisible1">
            <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
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
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="goBack">返回</el-button>

                </el-form-item>
            </el-form>
            </el-dialog>
        </div>
        <div>
            <el-dialog title="添加用户" :visible.sync="dialogFormVisible2">
                <el-form style="width: 60%" :model="ruleForm1" :rules="rules" ref="ruleForm1" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户姓名" prop="name">
                        <el-input v-model="ruleForm1.name"></el-input>
                    </el-form-item>
                    <el-form-item label="职工号" prop="postId">
                        <el-input v-model="ruleForm1.postId"></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="gender">
                        <el-radio-group v-model="ruleForm1.gender">
                            <el-radio label="男"></el-radio>
                            <el-radio label="女"></el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="登录账号" prop="username">
                        <el-input v-model="ruleForm1.username"></el-input>
                    </el-form-item>
                    <el-form-item label="用户密码" prop="password">
                        <el-input v-model="ruleForm1.password" show-password></el-input>
                    </el-form-item>
                    <el-form-item label="出生日期" prop="birthday">
                        <el-date-picker
                                v-model="ruleForm1.birthday"
                                type="date"
                                placeholder="选择日期时间"
                                format="yyyy 年 MM 月 dd 日"
                                value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="民族" prop="nation">
                        <el-input v-model="ruleForm1.nation" ></el-input>
                    </el-form-item>
                    <el-form-item label="籍贯" prop="nativePlace">
                        <el-input v-model="ruleForm1.nativePlace" ></el-input>
                    </el-form-item>
                    <el-form-item label="用户邮箱" prop="email">
                        <el-input v-model="ruleForm1.email"></el-input>
                    </el-form-item>
                    <el-form-item label="单位" prop="department">
                        <el-select v-model="ruleForm1.department" placeholder="请选择所在单位">
                            <el-option label="西南大学计算机与信息科学学院" value="西南大学计算机与信息科学学院"></el-option>
                            <el-option label="其他" value="其他"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="职位" prop="position">
                        <el-select v-model="ruleForm1.position" placeholder="请选择所任职位">
                            <el-option label="教授" value="教授"></el-option>
                            <el-option label="副教授" value="副教授"></el-option>
                            <el-option label="讲师" value="讲师"></el-option>
                            <el-option label="教学秘书" value="教学秘书"></el-option>
                            <el-option label="其他" value="其他"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="角色权限" prop="role">
                        <el-select v-model="ruleForm1.role.id" placeholder="请选择角色权限">
                            <el-option label="超级管理员" value="1"></el-option>
                            <el-option label="学术秘书" value="2"></el-option>
                            <el-option label="学术委员" value="3"></el-option>
                            <el-option label="普通用户" value="4"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm2('ruleForm1')">创建</el-button>
                        <el-button @click="goBack2">返回</el-button>

                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>
    <div>
    <el-table
            :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize) "
            stripe
            v-loading="loading"
            element-loading-text="正在加载..."
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)"
            style="width: 100%"
            :header-cell-style="{background:'#eef1f6',color:'#606266'}">
        <el-table-column
                type="selection"
                width="55">
        </el-table-column>
        <el-table-column
                fixed
                prop="id"
                label="编号"
                width="50">
        </el-table-column>
        <el-table-column
                prop="name"
                label="姓名"
                width="80">
        </el-table-column>
        <el-table-column
                label="账号状态"
                width="110">
            <template slot-scope="scope">
                <el-button type="text"   size="small" v-if="!scope.row.enabled">过期</el-button>
                <el-button type="text"   size="small" v-else>启用</el-button>
            </template>
        </el-table-column>
        <el-table-column
                prop="postId"
                label="职工号"
                width="100">
        </el-table-column>
        <el-table-column
                prop="gender"
                label="性别"
                width="50">
        </el-table-column>
        <el-table-column
                prop="birthday"
                label="出生日期"
                width="120">
        </el-table-column>
        <el-table-column
                prop="nation"
                label="民族"
                width="50">
        </el-table-column>
        <el-table-column
                prop="nativePlace"
                label="籍贯"
                width="80">
        </el-table-column>
        <el-table-column
                prop="department"
                label="单位"
                width="200">
        </el-table-column>
        <el-table-column
                prop="position"
                label="职位"
                width="80">
        </el-table-column>
        <el-table-column
                prop="username"
                label="账号"
                width="120">
        </el-table-column>
        <el-table-column
                prop="password"
                label="密码"
                width="120">
        </el-table-column>
        <el-table-column
                prop="email"
                label="邮箱"
                width="150">
        </el-table-column>
        <el-table-column
                prop="role.role_name"
                label="角色权限"
                width="100">
        </el-table-column>
        <el-table-column
                fixed="right"
                label="操作"
                width="100">
            <template slot-scope="scope">
                <el-button @click="editClick(scope.row)" icon="el-icon-edit" type="primary" circle size="small" ></el-button>
                <el-button @click="deleteUser(scope.row)" icon="el-icon-delete" type="danger"circle size="small"></el-button>
            </template>
        </el-table-column>
    </el-table>
        <el-pagination
                background
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5, 10, 20, 40]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="tableData.length">
        </el-pagination>
    </div>

    </div>
</template>

<script>
    import axios from  'axios'
    import Card from "../../components/chat/card";
    export default {
        components: {Card},
        methods: {
            editClick(row)  {
                this.dialogFormVisible1=true;
                this.ruleForm=row;
            },
            deleteUser(row){
                    this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        const _this=this
                        let url='http://localhost:8010/user/deleteById/'+row.id;
                        _this.axios.delete(url).then(function(resp){
                           /* _this.$alert(row.name+'删除成功!', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {*/

                           /*     }
                            });*/
                        })
                        this.$message({
                            type: 'success',
                            message: row.name+'删除成功!'
                        });
                        window.location.reload()
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });



            },
            handleSizeChange: function (size) {
                this.pagesize = size;
                console.log(this.pagesize)
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)
            },
            onError(err, file, fileList) {
                this.importDataBtnText = '导入数据';
                this.importDataBtnIcon = 'el-icon-upload2';
                this.importDataDisabled = false;
            },
            onSuccess(response, file, fileList) {
                this.importDataBtnText = '导入数据';
                this.importDataBtnIcon = 'el-icon-upload2';
                this.importDataDisabled = false;
                window.location.reload();
            },
            beforeUpload() {
                this.importDataBtnText = '正在导入';
                this.importDataBtnIcon = 'el-icon-loading';
                this.importDataDisabled = true;
            },
            exportData() {
                let url='http://localhost:8010/excl/export';
                this.axios.get(url).then(function (resp) {

                })
                //window.open("http://localhost:8010/excl/export", "_parent")
            },
            sendToAddUser(){
               this.dialogFormVisible2=true;
            },
            initUser(){
                const _this=this
                _this.loading=true
                _this.dialogFormVisible=true
                console.log(_this.keyword)
                let url='http://localhost:8010/user/findByName/'+this.keyword;
                _this.axios.get(url).then(function(resp){
                    _this.loading = false;
                        _this.user = resp.data;
                       // this.total = resp.total;

                });
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            selectByRole(role){
                let _this=this;
                if(role==='applicant'){
                    _this.findByRole(role);
                }else if (role==='reviewer'){
                    _this.findByRole(role);
                } else if (role==='admin'){
                    _this.findByRole(role);
                }else if (role==='assistant'){
                    _this.findByRole(role);
                }else if (role==='findAll'){
                    _this.findAll(role);
                }
            },
            findByRole(role){
                const _this=this
                let url='http://localhost:8060/userservice/user/findByRole/'+role;
                _this.axios.get(url).then(function(resp){
                    _this.tableData=resp.data
                    /* _this.tableData = resp.data.content
                     _this.pageSize = resp.data.size
                     _this.total = resp.data.totalElements*/
                })

            },
            findAll(role){
                const _this=this
                let url='http://localhost:8060/userservice/user/findAll';
                _this.axios.get(url).then(function(resp){
                    _this.tableData=resp.data

                })

            },
            submitForm(formName) {
                const  _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url='http://localhost:8060/userservice/user/update';
                        _this.axios.put(url,this.ruleForm).then(function (resp) {
                            if (resp.status===200) {
                                _this.$alert(_this.ruleForm.name + '修改成功', 'OK', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                       _this.dialogFormVisible1=false;
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
            submitForm2(formName) {
                const  _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url='http://localhost:8060/userservice/user/save';
                        _this.axios.post(url,this.ruleForm1).then(function (resp) {
                            if (resp.status===200) {
                                _this.$alert(_this.ruleForm1.name+'创建成功', 'OK', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.dialogFormVisible2=false;
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
            goBack(formName) {
               this.dialogFormVisible1=false;
            },
            goBack2(formName) {
                this.dialogFormVisible2=false;
            }

        },

        data() {
            return {
                tableData: [],
                files:[],
                pagesize: 5,
                role:'',
                currentPage:1,
                fileUploadBtnText:'',
                importDataBtnText: '导入数据',
                importDataBtnIcon: 'el-icon-upload2',
                importDataDisabled: false,
                loading: false,
                keyword:'',
                user: [],
                dialogFormVisible:false,
                dialogFormVisible1:false,
                dialogFormVisible2:false,
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
                ruleForm1: {
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
                        { min: 4, max: 20, message: '长度在4到20个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入用户密码', trigger: 'blur' },
                        { min: 4, max: 20, message: '长度在4到20个字符', trigger: 'blur' }
                    ],
                    nation: [
                        { required: true, message: '请输入民族', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在1到10个字符', trigger: 'blur' }
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
                        {  required: true, message: '请选择一个部门', trigger: 'change' }
                    ],
                    position: [
                        {  required: true, message: '请选择一个职位', trigger: 'change'}
                    ],
                    role: [
                        { required: true, message: '请选择一个角色', trigger: 'change' }
                    ]

                }

            }
        },
      /*  created() {
            const _this=this
            axios.get('http://localhost:8010/user/findAll').then(function(resp){
                _this.tableData=resp.data
               /!* _this.tableData = resp.data.content
                _this.pageSize = resp.data.size
                _this.total = resp.data.totalElements*!/
            })
        }*/
    }
</script>
