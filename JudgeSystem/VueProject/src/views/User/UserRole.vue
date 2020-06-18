<template>
    <div>
        <el-card>
        <div align="right"> <el-button type="primary" icon="el-icon-plus" size="mini" @click="addMenu">
            添加用户
        </el-button></div>
        <el-table
                :data="tableData1.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                style="width: 100%"
                :header-cell-style="{background:'#eef1f6',color:'#606266'}">
            <el-table-column
                    fixed
                    prop="id"
                    label="编号"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="role_name"
                    label="角色名"
                    width="250">
            </el-table-column>
            <el-table-column
                    prop="role"
                    label="角色代号"
                    width="250">
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="描述"
                    width="240">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="editClick(scope.row)" icon="el-icon-edit" type="primary" circle size="small" :disabled="dialogFormVisible"></el-button>
                    <el-button @click="deleteJob(scope.row)" icon="el-icon-delete" type="danger"circle size="small"></el-button>
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
                :total="tableData1.length">
        </el-pagination>
        <div class="row" id="pdfDom" style="padding-top: 15px;background-color:#fff;"><br>
            <el-dialog title="修改菜单" :visible.sync="dialogFormVisible">
                <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">

                    <el-form-item label="角色名" prop="role_name">
                        <el-input v-model="ruleForm.role_name"></el-input>
                    </el-form-item>
                    <el-form-item label="角色英文名" prop="role">
                        <el-input v-model="ruleForm.role"></el-input>
                    </el-form-item>
                    <el-form-item label="描述" prop="description">
                        <el-input type="textarea" v-model="ruleForm.description"></el-input>
                    </el-form-item>
                 <!--   <el-form-item label="显示">
                        <el-switch v-model="ruleForm.show_auth"active-color="#13ce66"
                                   inactive-color="#ff4949"  active-text="显示"
                                   inactive-text="隐藏"></el-switch>
                    </el-form-item>-->
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                        <el-button @click="goBack('ruleForm')">返回</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>
        <div class="row" id="pdfDom2" style="padding-top: 15px;background-color:#fff;"><br>
            <el-dialog title="添加菜单" :visible.sync="dialogFormVisible2">
                <el-form style="width: 60%" :model="ruleForm2" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">

                    <el-form-item label="角色名" prop="role_name">
                        <el-input v-model="ruleForm2.role_name"></el-input>
                    </el-form-item>
                    <el-form-item label="角色英文名" prop="role">
                        <el-input v-model="ruleForm2.role"></el-input>
                    </el-form-item>
                    <el-form-item label="描述" prop="description">
                        <el-input type="textarea" v-model="ruleForm2.description"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm2('ruleForm')">提交</el-button>
                        <el-button @click="goBack2('ruleForm')">返回</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>
        </el-card>
    </div>
</template>

<script>
    import axios from  'axios'
    export default {
        methods: {
            editClick(row)  {

                this.dialogFormVisible=true;
                this.ruleForm=row;



            },
            deleteJob(row){
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this=this
                    let url='http://localhost:8060/userservice/role/delete/'+row.id;
                    _this.axios.delete(url).then(function(resp){
                        /* _this.$alert(row.id+'删除成功!', 'OK', {
                             confirmButtonText: '确定',
                             callback: action => {*/
                        window.location.reload()
                        /*     }
                         });*/
                    })
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
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
            changeSwitch(data){
                console.log(data)
            },
            submitForm(formName) {
                const _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(_this.ruleForm)
                        let url='http://localhost:8060/userservice/role/update';
                        _this.axios.put(url,_this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.role_name + '修改成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.dialogFormVisible=false
                                }
                            });
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            submitForm2(formName) {
                const _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(_this.ruleForm2)
                        let url='http://localhost:8060/userservice/role/insert';
                        _this.axios.post(url,_this.ruleForm2).then(function (resp) {
                            _this.$alert(_this.ruleForm2.role_name + '添加成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.dialogFormVisible2=false
                                }
                            });
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            goBack(formName) {
                /*this.$refs[formName].resetFields();*/
                this.dialogFormVisible=false;
            },
            goBack2(formName) {
                /*this.$refs[formName].resetFields();*/
                this.dialogFormVisible2=false;
            },
            addMenu(){
                this.dialogFormVisible2=true;
            }
        },

        data() {
            return {
                tableData1: [],
                pagesize: 5,
                currentPage:1,
                dialogFormVisible:false,
                dialogFormVisible2:false,
                ruleForm: {
                  id:'',
                    role_name:'',
                    role:'',
                    description:''
                },
                ruleForm2: {
                    id:'',
                    role_name:'',
                    role:'',
                    description:''
                },
                rules: {
                    role_name: [
                        { required: true, message: '请输入角色名', trigger: 'blur' },
                        { min: 2, max: 50, message: '长度在 2 到 50个字符', trigger: 'blur' }
                    ],
                    role: [
                        { required: true, message: '请输入角色英文名', trigger: 'blur' },
                        { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
                    ],
                    description: [
                        { required: true, message: '请输入描述', trigger: 'blur' },
                        { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
                    ],

                },
            }
        },
        created() {
            const _this=this
            let url='http://localhost:8060/userservice/role/findAll';
            _this.axios.get(url).then(function(resp){
                _this.tableData1=resp.data
                // console.log(_this.tableData1)
            })
        }
    }
</script>
