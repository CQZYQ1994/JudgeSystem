<template>
    <div>
        <el-card>
       <div align="right"> <el-button type="primary" icon="el-icon-plus" @click="sendToAddMeet">
            添加会议
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
                    prop="create_name"
                    label="会议发起人"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="topic"
                    label="议题"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="place"
                    label="会议地点"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="participants"
                    label="与会人员"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="create_time"
                    label="会议发起时间"
                    width="180">
            </el-table-column>
            <el-table-column
                    label="会议状态"
                    width="110">
                <template slot-scope="scope">
                    <el-button type="text"   size="small" v-if="!scope.row.meet_status">过期</el-button>
                    <el-button type="text"   size="small" v-else>启用</el-button>
                </template>
            </el-table-column>
            <el-table-column
                    prop="document_path"
                    label="会议资料"
                    width="100">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="180">
                <template slot-scope="scope">
                    <el-button @click="editClick(scope.row)" icon="el-icon-edit" type="primary" circle size="small" ></el-button>
                    <el-button type="primary" circle size="small" icon="el-icon-plus" @click="createMeetTimeTable(scope.row)"></el-button>
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
        <div>
            <el-dialog title="修改会议信息" :visible.sync="dialogFormVisible">
                <el-form style="width: 50%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">

                    <el-form-item label="会议创建人" prop="create_name">
                        <el-input v-model="ruleForm.create_name"></el-input>
                    </el-form-item>
                    <el-form-item label="会议议题" prop="topic">
                        <el-input v-model="ruleForm.topic"></el-input>
                    </el-form-item>
                    <el-form-item label="会议地点" prop="place">
                        <el-input v-model="ruleForm.place"></el-input>
                    </el-form-item>
                    <el-form-item label="与会人员" prop="participants">
                        <el-input v-model="ruleForm.participants"></el-input>
                    </el-form-item>
                    <el-form-item label="会议状态">
                        <el-switch v-model="ruleForm.meet_status"active-color="#13ce66"
                                   inactive-color="#ff4949"  active-text="启用"
                                   inactive-text="过期"></el-switch>
                    </el-form-item>
                    <el-form-item label="会议创建时间" prop="create_time">
                        <el-date-picker
                                v-model="ruleForm.create_time"
                                type="datetime"
                                placeholder="选择日期时间"
                                format="yyyy年MM月dd日 HH:mm:ss"
                                value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <!-- <el-form-item label="建议会议时间2" >
                         <el-date-picker
                                 v-model="ruleForm.meeting_time2"
                                 type="datetime"
                                 placeholder="选择日期时间"
                                 format="yyyy年MM月dd日 HH:mm:ss"
                                 value-format="yyyy-MM-dd HH:mm:ss">
                         </el-date-picker>
                     </el-form-item>
                     <el-form-item label="建议会议时间3" >
                         <el-date-picker
                                 v-model="ruleForm.meeting_time3"
                                 type="datetime"
                                 placeholder="选择日期时间"
                                 format="yyyy年MM月dd日 HH:mm:ss"
                                 value-format="yyyy-MM-dd HH:mm:ss">
                         </el-date-picker>
                     </el-form-item>
                     <el-form-item label="会议附件" >
                         <el-input v-model="ruleForm.document_path"></el-input>
                     </el-form-item>-->


                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                        <el-button @click="goBack('ruleForm')">返回</el-button>
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
                    let url='http://localhost:8012/meetInfo/deleteById/'+row.id;
                    _this.axios.delete(url).then(function(resp){
                                window.location.reload();
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
            createMeetTimeTable(row){
                this.$router.push({
                    path:'/MeetTimeAdd',
                    query:{
                        id:row.id
                    }
                })

            },
            sendToAddMeet(){
                this.$router.push('/MeetAdd');
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
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let url='http://localhost:8012/meetInfo/update';
                        _this.axios.put(url,this.ruleForm).then(function (resp) {
                            _this.$alert(_this.ruleForm.topic + '会议表修改成功', 'OK', {
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
            goBack(formName) {
                /*this.$refs[formName].resetFields();*/
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
                    create_name: '',
                    topic:'',
                    place:'',
                    participants:'',
                    create_time:'',
                    meet_status:'',
                    meetingTimes:[{
                        id:'',
                        meetingInfoId:'',
                        vote:'',
                        meetTime:''
                    }],
                },
                rules: {
                    create_name: [
                        { required: true, message: '请输入创建人姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5个字符', trigger: 'blur' }
                    ],
                    topic: [
                        { required: true, message: '请输入会议议题', trigger: 'blur' },
                        { min: 2, max:50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
                    ],
                    place: [
                        { required: true, message: '请输入会议地点', trigger: 'blur' },
                        { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
                    ],
                    participants: [
                        { required: true, message: '请输入与会人员', trigger: 'blur' },
                        { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
                    ],
                    create_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],

                }

            }
        },
        created() {
            const _this=this
            let url='http://localhost:8012/meetInfo/findAll';
            _this.axios.get(url).then(function(resp){
                _this.tableData1=resp.data
            })
        }
    }
</script>
