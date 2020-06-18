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
                <el-table-column label="历史任务id"  align="center" width="150" fixed>
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskHisId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="任务名" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskHisName }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="任务开始时间" align="center" width="250">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskStartTime }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="任务结束时间" align="center" width="250">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskEndTime }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程实例id" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskProcInsId }}</span>
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
                       <!-- <el-button type="primary" @click="handleTaskHis(row)" size="small">查看历史记录</el-button>-->
                        <el-button type="primary" @click="handleProcImage(row)" size="small">查看流程图</el-button>
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
        <el-dialog title="任务历史" :visible.sync="dialogFormVisible1">
            <el-table
                    key=2
                    v-loading="loading"
                    :data="tableData1.slice((currentPage-1)*pagesize,currentPage*pagesize) "
                    fit
                    stripe
                    highlight-current-row
                    style="width: 100%;"
                    :header-cell-style="{background:'#eef1f6',color:'#606266'}"
            >
                <el-table-column label="任务id"  align="center" width="150" fixed>
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskHisId}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="任务名" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskHisName }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="任务流程实例id" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskProcInsId}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="任务开始时间" align="center" width="250">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskStartTime }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="任务结束时间" align="center" width="250">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskEndTime }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="用户相关信息" align="center" width="250">
                    <template slot-scope="scope">
                        <span>{{ scope.row.taskUser }}</span>
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
            <el-button type="primary" @click="goback" size="small">返回</el-button>
        </el-dialog>
        <el-dialog title="流程图" :visible.sync="dialogFormVisible2" width="70%">
            <img :src="urlImage+this.processInstanceId">
            <el-button @click="goback2">返回</el-button>

        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "ApplyHistory",
        data() {
            return {
                pagesize: 5,
                currentPage:1,
                dialogFormVisible1:false,
                dialogFormVisible2:false,
                tableData:[],
                tableData1:[],
                loading: false,
                list:[],
                tableKey:1,
                responseResult: {
                    code: '',
                    data: '',
                    msg: ''
                },
                username:JSON.parse(window.localStorage.getItem('token' || '[]')).name,
                urlImage:"http://localhost:8011/activiti/processIns/procImage/",
                processInstanceId:'',
                status:'',
                TaskHistory:{
                     taskHisId:'',
                    taskHisName:'',
                    taskProcInsId:'',
                    taskStartTime:'',
                    taskEndTime:'',
                    taskDurationInMillis:'',
                    taskUser:''

                }
            }
        },

        methods: {



            handleTaskHis(row){
                const _this=this;
                _this.dialogFormVisible1=true;
                let url='http://localhost:8011/activiti/processIns/findHisTask/'+row.procInsId;
                _this.axios.get(url).then(function (resp) {
                    _this.tableData1=resp.data;
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

        },
        created() {
            const _this=this

            let url='http://localhost:8011/activiti/review/findHisTask/'+this.username;
            console.log(url)
            _this.axios.get(url).then(function(resp) {
                if(resp.status===200){
                    console.log(resp.data)
                    _this.tableData=resp.data}
            })

        }
    }
</script>

<style scoped>

</style>
