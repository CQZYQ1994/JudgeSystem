<template>
    <div>
        <el-card>
       <div align="right"> <el-button type="primary" icon="el-icon-plus" size="mini" @click="addMenu">
            添加用户
        </el-button></div>
        <el-table
                :data="tableData1.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                style="width: 100%"
                row-key="id"
                default-expand-all
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
                :header-cell-style="{background:'#eef1f6',color:'#606266'}">>
            <el-table-column
                    prop="name"
                    label="名称"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="url"
                    label="路径"
                    width="300">
            </el-table-column>
            <el-table-column
                    prop="path"
                    label="路由"
                    width="150">
            </el-table-column>
           <!-- <el-table-column
                    label="评审状态"
                    width="110">
                <template slot-scope="scope">
                    <el-button type="text"   size="small" v-if="!scope.row.review_status">未审核</el-button>
                    <el-button type="text"   size="small" v-else>已审核</el-button>
                </template>
            </el-table-column>-->
            <el-table-column
                    prop="component"
                    label="组件"
                    width="140">
            </el-table-column>
            <el-table-column
                    prop="show"
                    label="显示"
                    width="100">
                <template slot-scope="scope">
                    <el-button type="text"   size="small" v-if="scope.row.show_auth">显示</el-button>
                    <el-button type="text"   size="small" v-else>隐藏</el-button>
                </template>
            </el-table-column>

            <el-table-column
                    prop="iconCls"
                    label="图标"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="meta.keep_alive"
                    label="展开"
                    width="120">
                <template slot-scope="scope">
                    <el-button type="text"   size="small" v-if="!scope.row.meta.keep_alive">不展开</el-button>
                    <el-button type="text"   size="small" v-else>展开</el-button>
                </template>
            </el-table-column>
            <el-table-column
                    prop="meta.require_auth"
                    label="是否验证"
                    width="100">
                <template slot-scope="scope">
                    <el-button type="text"   size="small" v-if="!scope.row.meta.require_auth">否</el-button>
                    <el-button type="text"   size="small" v-else>是</el-button>
                </template>
            </el-table-column>
            <el-table-column
                    prop="parent_id"
                    label="父菜单"
                    width="100">
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

                    <el-form-item label="URL" prop="url">
                        <el-input v-model="ruleForm.url"></el-input>
                    </el-form-item>
                    <el-form-item label="路由" prop="path">
                        <el-input v-model="ruleForm.path"></el-input>
                    </el-form-item>
                    <el-form-item label="组件" prop="component">
                        <el-input v-model="ruleForm.component"></el-input>
                    </el-form-item>
                    <el-form-item label="显示">
                        <el-switch v-model="ruleForm.show_auth"active-color="#13ce66"
                                   inactive-color="#ff4949"  active-text="显示"
                                   inactive-text="隐藏"></el-switch>
                </el-form-item>
                    <el-form-item label="名称" prop="name">
                        <el-input v-model="ruleForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="图标" >
                        <el-input v-model="ruleForm.iconCls"></el-input>
                    </el-form-item>
                    <el-form-item label="父菜单" >
                        <el-input v-model="ruleForm.parent_id"></el-input>
                    </el-form-item>
                    <el-form-item label="展开">
                        <el-switch v-model="ruleForm.meta.keep_alive"active-color="#13ce66"
                                   inactive-color="#ff4949"  active-text="展开"
                                   inactive-text="不展开"></el-switch>
                    </el-form-item>
                    <el-form-item label="验证">
                        <el-switch v-model="ruleForm.meta.require_auth"active-color="#13ce66"
                                   inactive-color="#ff4949"  active-text="需要验证"
                                   inactive-text="不需验证"></el-switch>
                    </el-form-item>
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

                    <el-form-item label="URL" prop="url">
                        <el-input v-model="ruleForm2.url"></el-input>
                    </el-form-item>
                    <el-form-item label="路由" prop="path">
                        <el-input v-model="ruleForm2.path"></el-input>
                    </el-form-item>
                    <el-form-item label="组件" prop="component">
                        <el-input v-model="ruleForm2.component"></el-input>
                    </el-form-item>
                    <el-form-item label="显示">
                        <el-switch v-model="ruleForm2.show_auth"active-color="#13ce66"
                                   inactive-color="#ff4949"  active-text="显示"
                                   inactive-text="隐藏"></el-switch>
                    </el-form-item>
                    <el-form-item label="名称" prop="name">
                        <el-input v-model="ruleForm2.name"></el-input>
                    </el-form-item>
                    <el-form-item label="图标" >
                        <el-input v-model="ruleForm2.iconCls"></el-input>
                    </el-form-item>
                    <el-form-item label="父菜单" >
                        <el-input v-model="ruleForm2.parent_id"></el-input>
                    </el-form-item>
                    <el-form-item label="展开">
                        <el-switch v-model="ruleForm2.meta.keep_alive"active-color="#13ce66"
                                   inactive-color="#ff4949"  active-text="展开"
                                   inactive-text="不展开"></el-switch>
                    </el-form-item>
                    <el-form-item label="验证">
                        <el-switch v-model="ruleForm2.meta.require_auth"active-color="#13ce66"
                                   inactive-color="#ff4949"  active-text="需要验证"
                                   inactive-text="不需验证"></el-switch>
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
                    let url='http://localhost:8010/menu/deleteById/'+row.id;
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
                _this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(_this.ruleForm)
                        let url='http://localhost:8060/userservice/menu/update';
                        _this.axios.put(url,_this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.name + '菜单修改成功', 'OK', {
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
                _this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(_this.ruleForm2)
                        _this.axios.post('http://localhost:8060/userservice/menu/save',_this.ruleForm2).then(function (resp) {
                            _this.$alert(_this.ruleForm2.name + '菜单添加成功', 'OK', {
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
                    url: '',
                    path:'',
                    component:'',
                    show_auth:'',
                    name:'',
                    iconCls:'',
                    meta:{
                        keep_alive:'',
                        require_auth:'',
                    },
                    parent_id: '',
                },
                ruleForm2: {
                    url: '',
                    path:'',
                    component:'',
                    show_auth:'',
                    name:'',
                    iconCls:'',
                    meta:{
                        keep_alive:'',
                        require_auth:'',
                    },
                    parent_id: '',
                },
                rules: {
                    url: [
                        { required: true, message: '请输入URL', trigger: 'blur' },
                        { min: 2, max: 50, message: '长度在 2 到 50个字符', trigger: 'blur' }
                    ],
                    path: [
                        { required: true, message: '请输入组件路由', trigger: 'blur' },
                        { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
                    ],
                    component: [
                        { required: true, message: '请输入组件', trigger: 'blur' },
                        { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入菜单名称', trigger: 'blur' },
                        { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
                    ],
                },
            }
        },
        created() {
            const _this=this
            let url='http://localhost:8060/userservice/menu/getAllMenu';
            _this.axios.get(url).then(function(resp){
                _this.tableData1=resp.data
               // console.log(_this.tableData1)
            })
        }
    }
</script>
