<template>
    <div>
        <el-card>
        <el-dialog title="修改申请" :visible.sync="dialogFormVisible">
            <el-form style="width: 60%" :model="ruleForm"  ref="ruleForm" label-width="120px" class="demo-ruleForm">
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
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="getBack">回退</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <el-table
                :data="tableData1.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                :header-cell-style="{background:'#eef1f6',color:'#606266'}"
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="id"
                    label="编号"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="申请人姓名"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="topic"
                    label="申请类别"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="描述"
                    width="220">
            </el-table-column>
            <el-table-column
                    label="审核状态"
                    width="100">
                <template slot-scope="scope">
                    <el-button type="text"   size="small" v-if="!scope.row.review_status">未审核</el-button>
                    <el-button type="text"   size="small" v-else>已审核</el-button>
                </template>
            </el-table-column>
            <el-table-column
                    prop="review_opinion"
                    label="评审意见"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="review_result"
                    label="评审结果"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="review_vote"
                    label="得票数"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="create_time"
                    label="申请创建时间"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="update_time"
                    label="评审时间"
                    width="200">
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
                    let url='http://localhost:8015/apply/deleteById/'+row.id;
                    _this.axios.delete(url).then(function(resp){
                    /*    _this.$alert(row.id+'删除成功!', 'OK', {
                            confirmButtonText: '确定',
                            callback: action => {*/
                                window.location.reload()
                      /*      }
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
            submitForm(formName) {
                const _this=this
                _this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url='http://localhost:8015/apply/update';
                        _this.axios.put(url,_this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.name + '修改成功', 'OK', {
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
            getBack(){
                this.dialogFormVisible=false;
            }

        },

        data() {
            return {
                tableData1: [],
                pagesize: 5,
                currentPage:1,
                dialogFormVisible:false,
                ruleForm: {
                    id:'',
                    name:'',
                    topic:'',
                    description:'',
                    review_status:false,
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
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5个字符', trigger: 'blur' }
                    ],
                    topic: [
                        { required: true, message: '请输入申请类别', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    description: [
                        { required: true, message: '请输入申请描述', trigger: 'blur' },
                        { min: 5, max: 50, message: '长度在 5 到 200 个字符', trigger: 'blur' }
                    ],
                    document: [
                        { required: true, message: '请输入上传文件名', trigger: 'blur' },
                        { min: 1,  message: '至少一个字符', trigger: 'blur' }
                    ],
                    create_time: [
                        {  required: true, message: '请选择时间', trigger: 'change' }
                    ],
                    processDefKey: [
                        { required: true, message: '请选择', trigger: 'change' }
                    ],
                    update_time: [
                        {  required: true, message: '请选择时间', trigger: 'change' }
                    ]


                },
            }
        },
        created() {
            const _this=this
            let url='http://localhost:8015/apply/findAll';
            _this.axios.get(url).then(function(resp){
                _this.tableData1=resp.data
            })
        }
    }
</script>
