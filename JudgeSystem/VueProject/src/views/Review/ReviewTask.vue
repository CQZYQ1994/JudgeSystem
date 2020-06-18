<template>
    <div class="process-container">
        <el-card>
            <el-table
                    :key="tableKey"
                    v-loading="loading"
                    :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize) "
                    fit
                    stripe
                    highlight-current-row
                    style="width: 100%;"
                    :header-cell-style="{background:'#eef1f6',color:'#606266'}"
            >
                <el-table-column label="任务id"  align="center" width="150" fixed>
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="任务名" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskName }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="任务开始时间" align="center" width="250">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskCreateTime }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="任务代理人" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskAssignee }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程实例id" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskProcInsId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程定义id" align="center" width="250">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskProcDefId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="执行对象id" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskExecutionId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="申请表业务id" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.businessKey }}</span>
                    </template>
                </el-table-column>

                <el-table-column
                        fixed="right"
                        label="操作"
                        align="center"
                        width="150"
                        class-name="small-padding fixed-width"
                >
                    <template slot-scope="{row}">
                        <el-button type="primary" @click="comTask(row)" size="small">评审</el-button>
                        <el-button type="primary" @click="handleProcImage(row)" size="small">查看流程图</el-button>
                        <el-button type="primary" @click="competeTask(row)" size="small">提交任务</el-button>
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
        </el-card>
        <el-dialog title="申请业务表" :visible.sync="dialogFormVisible1">
            <el-form align="center" style="width: 80%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">

                <el-form-item label="申请人姓名" prop="name">
                    <el-input v-model="ruleForm.name" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="申请主题" prop="topic">
                    <el-input v-model="ruleForm.topic" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="description">
                    <el-input type="textarea" v-model="ruleForm.description" readonly="true"></el-input>
                </el-form-item>
                <!-- <el-form-item label="创建时间" >
                     <el-date-picker
                             readonly="true"
                             v-model="ruleForm.create_time"
                             type="datetime"
                             placeholder="选择日期时间"
                             format="yyyy年MM月dd日 HH:mm:ss"
                             value-format="yyyy-MM-dd HH:mm:ss">
                     </el-date-picker>
                 </el-form-item>-->
                <!-- <el-form-item label="修改时间" >
                     <el-date-picker
                             disabled="true"
                             v-model="ruleForm.update_time"
                             type="datetime"
                             placeholder="选择日期时间"
                             format="yyyy年MM月dd日 HH:mm:ss"
                             value-format="yyyy-MM-dd HH:mm:ss">
                     </el-date-picker>
                 </el-form-item>-->
                <!-- <el-form-item label="审核状态">

                     <el-switch v-model="ruleForm.review_status"active-color="#13ce66"
                                inactive-color="#ff4949"  active-text="已审"
                                inactive-text="未审" disabled="true"></el-switch>
                 </el-form-item>-->
                <el-form-item label="评审意见" prop="review_opinion">
                    <el-input type="textarea" v-model="ruleForm.review_opinion"></el-input>
                </el-form-item>
                <el-form-item label="评审结果" prop="review_result">
                    <el-select v-model="ruleForm.review_result" placeholder="请选择">
                        <el-option label="合格" value="合格"></el-option>
                        <el-option label="不合格" value="不合格"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button type="primary" @click="goback">返回</el-button>
                </el-form-item>
            </el-form>

        </el-dialog>
        <el-dialog title="流程图" :visible.sync="dialogFormVisible2" width="80%">
            <img :src="urlImage+this.processInstanceId">
            <el-button @click="goback2">返回</el-button>

        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "ReviewTask",
        data() {
            return {
                pagesize: 5,
                currentPage:1,
                dialogFormVisible1:false,
                dialogFormVisible2:false,
                tableData:[],
                tableData1:[],
                result:'',
                loading: false,
                list:[],
                tableKey:1,
                responseResult: {
                    code: '',
                    data: '',
                    msg: ''
                },
                role:JSON.parse(window.localStorage.getItem('token' || '[]')).role.role,
                username:JSON.parse(window.localStorage.getItem('token' || '[]')).name,
                urlImage:"http://localhost:8011/activiti/processIns/procImage/",
                processInstanceId:'',
                status:'',
                TaskInfo:{
                    taskId:'',
                    taskName:'',
                    taskCreateTime:'',
                    taskAssignee:'',
                    taskProcInsId:'',
                    taskExecutionId:'',
                    taskProcDefId:'',
                    businessKey:'',

                },
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
                    review_result: [
                        { required: true, message: '请选择', trigger: 'change' }
                    ],
                    review_opinion: [
                        { required: true, message: '请输入意见', trigger: 'blur' },
                        { min: 2, max: 50, message: '长度在 2 到 200 个字符', trigger: 'blur' }
                    ],





                },
            }
        },

        methods: {



            comTask(row){
                const _this=this;
                _this.dialogFormVisible1=true;
                let url='http://localhost:8015/apply/findById/'+row.businessKey;
                _this.axios.get(url).then(function (resp) {
                    _this.ruleForm=resp.data;
                })
            },
            handleProcImage(row){
                const _this=this
                _this.dialogFormVisible2=true;
                _this.processInstanceId=row.taskProcInsId;
            },
            goback(){
                this.dialogFormVisible1=false;
            },
            goback2(){
                this.dialogFormVisible2=false;
                this.processInstanceId='';
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
                        this.result=_this.ruleForm.review_result;
                        console.log(this.result)
                        let url='http://localhost:8015/apply/update';
                        _this.axios.put(url,_this.ruleForm).then(function (resp) {
                            _this.$alert('提交成功', 'OK', {
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
            competeTask(row){
                const _this=this;
                if (this.result===null){
                    alert("请先评审")
                } else{
                    let url='http://localhost:8011/activiti/review/comTask/'+row.taskId+'/'+_this.username+'/'+this.result;
                    console.log(url)
                    _this.axios.get(url).then(function (resp) {
                        if (resp.status===200){
                            console.log(resp.data)
                            //window.location.reload();
                        }
                    })
                }

            },

        },
        created() {
            const _this=this
            let url='http://localhost:8011/activiti/review/currentTask/'+_this.role;
            _this. axios.get(url).then(function(resp) {
                if(resp.status===200){
                    // console.log(_this.role)

                    _this.tableData=resp.data}
                //console.log(_this.tableData)
            })

        }
    }
</script>

<style scoped>

</style>
