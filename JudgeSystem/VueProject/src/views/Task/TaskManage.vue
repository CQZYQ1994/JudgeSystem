<template>
    <div>
        <el-card>
        <div>
        <el-dialog title="修改通知" :visible.sync="dialogFormVisible">
            <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                <el-form-item label="通知主题" prop="notifi_name">
                    <el-input v-model="ruleForm.notifi_name"></el-input>
                </el-form-item>
                <el-form-item label="通知描述" prop="notifi_desc">
                    <el-input type="textarea" v-model="ruleForm.notifi_desc"></el-input>
                </el-form-item>
                <el-form-item label="开始时间" prop="start_time">
                    <el-date-picker
                            v-model="ruleForm.start_time"
                            type="date"
                            placeholder="选择日期时间"
                            format="yyyy 年 MM 月 dd 日"
                            value-format="yyyy-MM-dd">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="截止时间" prop="end_time">
                    <el-date-picker
                            v-model="ruleForm.end_time"
                            type="date"
                            placeholder="选择日期时间"
                            format="yyyy 年 MM 月 dd 日"
                            value-format="yyyy-MM-dd">
                    </el-date-picker>
                </el-form-item>
                <!-- <el-form-item label="创建时间" prop="create_time">
                     <el-date-picker
                             v-model="ruleForm.create_time"
                             type="datetime"
                             placeholder="选择日期时间"
                             format="yyyy年MM月dd日 HH:mm:ss"
                             value-format="yyyy-MM-dd HH:mm:ss">
                     </el-date-picker>
                 </el-form-item>-->

                <el-form-item label="创建人" prop="create_user">
                    <el-input v-model="ruleForm.create_user"></el-input>
                </el-form-item>
                <el-form-item label="文件名" >
                    <el-input v-model="ruleForm.document"></el-input>
                </el-form-item>


                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="goback">返回</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
        <div>
            <el-dialog title="发布通知" :visible.sync="dialogFormVisible1">
                <el-form style="width: 60%" :model="ruleForm1" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                    <el-form-item label="通知主题" prop="notifi_name">
                        <el-input v-model="ruleForm1.notifi_name"></el-input>
                    </el-form-item>
                    <el-form-item label="通知描述" prop="notifi_desc">
                        <el-input type="textarea" v-model="ruleForm1.notifi_desc"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间" prop="start_time">
                        <el-date-picker
                                v-model="ruleForm1.start_time"
                                type="date"
                                placeholder="选择日期时间"
                                format="yyyy 年 MM 月 dd 日"
                                value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="截止时间" prop="end_time">
                        <el-date-picker
                                v-model="ruleForm1.end_time"
                                type="date"
                                placeholder="选择日期时间"
                                format="yyyy 年 MM 月 dd 日"
                                value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>

                    <el-form-item label="创建人" prop="create_user">
                        <el-input v-model="ruleForm1.create_user"></el-input>
                    </el-form-item>
                    <el-form-item label="文件名" prop="document">
                    <el-input v-model="ruleForm1.document"></el-input>
                </el-form-item>
                    <el-form-item>
                    <el-upload
                            class="upload-demo"
                            ref="upload"
                            action="http://localhost:8018/file/uploadFiles"
                            :on-preview="handlePreview"
                            :on-remove="handleRemove"
                            :file-list="files"
                            :auto-upload="false"
                            :before-upload="beforeUploadFile">
                        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
                        <div slot="tip" class="el-upload__tip">上传文件不宜超过200M</div>
                    </el-upload>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm1('ruleForm')">提交</el-button>
                        <el-button @click="goback1">返回</el-button>
                    </el-form-item>
                </el-form>

               </el-dialog>
        </div>
        <div align="right"> <el-button type="primary" icon="el-icon-plus" @click="addTask">
            添加任务
        </el-button></div>

        <el-table
                :data="tableData1.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                style="width: 100%"
                :header-cell-style="{background:'#eef1f6',color:'#606266'}">
            <el-table-column
                    fixed
                    prop="id"
                    label="编号"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="notifi_name"
                    label="通知"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="notifi_desc"
                    label="内容描述"
                    width="220">
            </el-table-column>
            <el-table-column
                    prop="start_time"
                    label="开始时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="end_time"
                    label="截止时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="create_time"
                    label="发布时间"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="create_user"
                    label="创建人"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="document"
                    label="附件资料"
                    width="120">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="editClick(scope.row)" icon="el-icon-edit" type="primary" circle size="small" ></el-button>
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
                    let url='http://localhost:8014/notify/deleteById/'+row.id;
                    _this.axios.put(url).then(function(resp){
                      /*  _this.$alert(row.id+'删除成功!', 'OK', {
                            confirmButtonText: '确定',
                            callback: action => {*/
                                window.location.reload()
                        /*    }
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
            addTask(){
                this.dialogFormVisible1=true;

            },
            submitForm(formName) {
                const  _this=this
                _this.$refs[formName].validate((valid) => {
                    if (valid) {

                        let url='http://localhost:8014/notify/update';
                        _this.axios.put(url,_this.ruleForm).then(function (resp) {

                            _this.$alert(_this.ruleForm.notifi_name+'修改成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.dialogFormVisible=false;

                                }
                            });

                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            goback() {
                this.dialogFormVisible=false;
            },
            submitForm1(formName) {
                const  _this=this
                _this.$refs[formName].validate((valid) => {
                    if (valid) {

                        let url='http://localhost:8014/notify/save';
                        _this.axios.post(url,_this.ruleForm1).then(function (resp) {
                               // console.log(_this.ruleForm1)
                            _this.$alert(_this.ruleForm1.notifi_name+'创建成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.dialogFormVisible1=false;

                                }
                            });

                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            goback1() {
                this.dialogFormVisible1=false;
            },
            submitUpload() {
                this.$refs.upload.submit();

            },
            handleRemove(file, files) {
                console.log(file, files);
            },
            handlePreview(file) {
                console.log(file);
            },
            beforeUploadFile(file){
                const isLt2M=file.size/1024/1024<200;
                if(!isLt2M){
                    this.$message.error('文件不能超过200M')
                }
                return isLt2M;
            }
        },

        data() {
            return {
                tableData1: [],
                files: [],
                dialogFormVisible:false,
                dialogFormVisible1:false,
                pagesize: 5,
                currentPage:1,
                ruleForm: {
                    id:'',
                    notifi_name:'',
                    notifi_desc:'',
                    start_time:'',
                    end_time:'',
                    create_time:'',
                    create_user:'',
                    del_sign:'',
                    document:'',

                },
                ruleForm1: {
                    id:'',
                    notifi_name:'',
                    notifi_desc:'',
                    start_time:'',
                    end_time:'',
                    create_time:'',
                    create_user:'',
                    del_sign:'',
                    document:'',

                },
                rules: {
                    notifi_name: [
                        { required: true, message: '请输入通知名', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    notifi_desc: [
                        { required: true, message: '请输入招聘人数', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],

                    start_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    end_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],

                    create_user: [
                        { required: true, message: '请输入联系人', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    document: [
                        { required: true, message: '请输入上传文件名', trigger: 'blur' },
                        { min: 1, message: '至少一个字符', trigger: 'blur' }
                    ],


                },

            }
        },
        created() {
            const _this=this
            let url='http://localhost:8014/notify/findAll';
            _this.axios.get(url).then(function(resp){
                _this.tableData1=resp.data
            })
        }
    }
</script>

<!--
<template>
    <div class="components-container">
        <div class="filter-container">
            <el-select v-model="tableQuery.status" clearable placeholder="请选择角色状态" style="width: 150px">
                <el-option v-for="item in statusOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
            <el-select v-model="tableQuery.type" clearable placeholder="请选择角色类型" style="width: 150px">
                <el-option v-for="item in typeOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
            <el-input v-model="tableQuery.name" @keyup.enter.native="handleFilter" style="width: 200px;" placeholder="角色名称"></el-input>
            <el-button style="margin-left: 10px;" @click="handleFilter" type="primary"><i class="el-icon-search"></i></el-button>
            <el-button @click="handleReset" type="primary"><i class="el-icon-refresh"></i></el-button>
        </div>

        <tree-table :tableData="tableData" @manage="displayManage" @update="updateDialog" @delete="deleteRecord" @create="createDialog">
        </tree-table>

        <el-dialog :title="formTitle" :visible.sync="formVisible">
            <el-form class="small-space" :model="createdItem" label-position="left" label-width="70px" style="width: 60%;">
                <el-form-item label="角色名称">
                    <el-input v-model="createdItem.name" placeholder="请填写角色名称"></el-input>
                </el-form-item>
                <el-form-item label="角色描述">
                    <el-input v-model="createdItem.desc" placeholder="请填写角色描述"></el-input>
                </el-form-item>
                <el-form-item label="角色状态">
                    <el-switch v-model="createdItem.status" :inactive-value="1" :active-value="0" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
                </el-form-item>
                <el-form-item label="角色类型">
                    <el-select v-model="createdItem.type" clearable placeholder="请选择角色类型">
                        <el-option v-for="item in typeOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="formVisible = false">取 消</el-button>
                <el-button type="primary" :loading="formSubmiting" @click="commitForm">确 定</el-button>
            </div>
        </el-dialog>

        <menu-manage ref="menuManage"> </menu-manage>

        <action-manage ref="actionManage"> </action-manage>

        <user-manage ref="userManage"> </user-manage>

    </div>
</template>

<script>
    import {confirmBox} from  '../../utils/message';
    import MenuManage from '../../views/system/roleOperate/MenuManage';
    import ActionManage from '../../views/system/roleOperate/ActionManage';
    import UserManage from '../../views/system/roleOperate/UserManage';
    import TreeTable from '../../views/system/roleOperate/TreeTable';
    export default {
        components: {
            MenuManage,
            ActionManage,
            UserManage,
            TreeTable,
        },
        data() {
            return {
                statusOptions: [
                    {
                        label: '正常',
                        value: 0
                    },
                    {
                        label: '停用',
                        value: 1
                    }
                ],
                typeOptions: [
                    {
                        label: '成员',
                        value: 0
                    },
                    {
                        label: '群组',
                        value: 1
                    }
                ],
                total: null,
                tableData: [],
                tableLoading: false,
                tableQuery: {
                    limit: 10,
                    currentPage: 1,
                    name: null,
                    status: null,
                    type: null,
                },
                formTitle: null,
                formVisible: false,
                formSubmiting: false,
                createdItem: {
                    id: null,
                    name: null,
                    desc: null,
                    status: 0,
                    type: 0,
                },
                deletedForm: {
                    id: null
                },
            };
        },
        mounted() {
            this.getRoleList();
        },
        methods: {
            getRoleList() {
                this.tableLoading = true;
                this.$api.system.role.list({
                    data: this.tableQuery
                }).then(res => {
                    this.tableData = res.data;
                    this.tableLoading = false;
                }).catch(error => {
                    this.tableLoading = false;
                    console.log(error);
                });
            },
            handleFilter() {
                this.getRoleList();
            },
            handleReset() {
                this.tableQuery.limit = 10;
                this.tableQuery.currentPage = 1;
                this.tableQuery.name = null;
                this.tableQuery.status = null;
                this.tableQuery.type = null;
                this.getRoleList();
            },
            clearDialog() {
                this.createdItem.id = null;
                this.createdItem.name = null;
                this.createdItem.desc = null;
                this.createdItem.status = 0;
                this.createdItem.type = 0;
            },
            createDialog(parentId) {
                this.formVisible = true;
                this.clearDialog();
                this.formTitle = parentId ? "新建子角色" : "新建角色";
                this.createdItem.parentId = parentId;
            },
            updateDialog(row) {
                this.formVisible = true;
                this.clearDialog();
                this.formTitle = "修改角色";
                this.createdItem = Object.assign({}, row);
            },
            commitForm() {
                this.formSubmiting = true;
                this.$api.system.role.save({
                    data: this.createdItem
                }).then(response => {
                    this.formSubmiting = false;
                    this.formVisible = false;
                    this.$notify({
                        title: '成功',
                        message: '保存成功',
                        type: 'success',
                        duration: 1500,
                    });
                    this.handleFilter();
                    console.log(response);
                }).catch(error => {
                    this.formSubmiting = false;
                    this.$notify({
                        title: '错误',
                        message: '保存失败',
                        type: 'error',
                        duration: 3000,
                    });
                    console.log(error);
                });
            },
            deleteRecord(id) {
                confirmBox('角色').then(() => {
                    this.deletedForm.id = id;
                    this.$api.system.role.delete({
                        data: this.deletedForm
                    }).then(response => {
                        this.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success',
                            duration: 1500,
                        });
                        this.handleFilter();
                        console.log(response);
                    }).catch(error => {
                        this.formSubmiting = false;
                        this.$notify({
                            title: '错误',
                            message: '删除失败',
                            type: 'error',
                            duration: 3000,
                        });
                        console.log(error);
                    });
                });
            },
            displayManage(id, type) {
                this.$nextTick(() => {
                    switch(type) {
                        case 'menu':
                            this.$refs.menuManage.getRoleMenu(id);
                            break;
                        case 'resource':
                            this.$refs.actionManage.getRoleResource(id);
                            break;
                        case 'user':
                            this.$refs.userManage.getRoleUser(id);
                            break;
                        default: break;
                    }
                });
            },
        }
    };
</script>-->
