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
                <el-table-column label="流程实例id"  align="center" width="150" fixed>
                    <template slot-scope="scope">
                        <span>{{ scope.row.processInsId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程定义id" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processDefId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程定义名" align="center" width="250">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processDefName }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程实例部署id" align="center" width="120">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processInsDepId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程定义key" align="center" width="200">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processDefKey }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程实例状态" align="center" width="120">
                    <template slot-scope="scope">
            <span v-if="!scope.row.isSuspended">
              <el-tag type="success">已激活</el-tag>
            </span>
                        <span v-else>
              <el-tag type="info">已挂起</el-tag>
            </span>
                    </template>
                </el-table-column>

                <el-table-column label="流程实例名" align="center" width="200">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processInsName }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程实例业务KEY" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processInsBusKey }}</span>
                    </template>
                </el-table-column>

                <el-table-column label="流程实例描述" align="center" width="200">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processInsDesc }}</span>
                    </template>
                </el-table-column>


                <!-- <el-table-column label="描述" prop="createTime" align="center" width="250">
                     <template slot-scope="scope">
                         <span>{{ scope.row.metaInfo}}</span>
                     </template>
                 </el-table-column>-->
                <el-table-column
                        fixed="right"
                        label="操作"
                        align="center"
                        width="150"
                        class-name="small-padding fixed-width"
                >
                    <template slot-scope="{row}">
                        <el-button type="primary" @click="handleSUS(row)" size="small">挂起</el-button>
                        <el-button type="primary" @click="handleACT(row)" size="small">激活</el-button>
                        <el-button type="primary" @click="handleImage(row)" size="small">查看流程</el-button>
                        <el-button type="primary" @click="deleteProcIns(row)" size="small">终止</el-button>
                        <!--<el-tooltip content="流程定义状态切换" placement="top">

                            <i class="el-icon-edit-outline operate-edit" @click="handleUpdate(row)" />
                        </el-tooltip>-->
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
            <el-dialog title="流程实例图" :visible.sync="dialogFormVisible" width="80%">
                <img :src="urlImage+this.processInstanceId">
                <el-button @click="goBack">返回</el-button>

            </el-dialog>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "WorkFlowProcessIns",
        data() {
            return {
                pagesize: 5,
                currentPage:1,
                tableData:[],
                loading: false,
                urlImage:"http://localhost:8011/activiti/processIns/procImage/",
                list:[],
                tableKey:1,
                responseResult: {
                    code: '',
                    data: '',
                    msg: ''
                },
                imageRes:[],
                dialogFormVisible:false,
                processInstanceId:'',
                status:'',
                ProcessInsInfo:{
                    processInsId: '',
                    processInsName: '',
                    processInsDesc: '',
                    processInsBusKey: '',
                    processInsDepId: '',
                    processDefId: '',
                    processDefKey: '',
                    processDefName:'',
                    isSuspended:'',

                }
            }
        },

        methods: {



            handleSUS(row) {
                const _this=this
                if(row.isSuspended===true){
                    alert("实例已经被挂起！")
                }else {
                    _this.processInstanceId=row.processInsId;
                    console.log(_this.processInstanceId)
                    let url='http://localhost:8011/activiti/processIns/susProcessIns/'+_this.processInstanceId;
                    _this.axios.get(url).then(function (resp) {
                        console.log(resp.data)
                        alert("实例挂起成功")
                    })
                }

            },
            handleACT(row) {
                const _this=this
                if(row.isSuspended===true){
                    _this.processInstanceId=row.processInsId;
                    console.log(_this.processInstanceId)
                    let url='http://localhost:8011/activiti/processIns/actProcessIns/'+_this.processInstanceId;
                    _this.axios.get(url).then(function (resp) {
                        console.log(resp.data)
                        alert("实例激活成功")
                    })
                }else {
                    alert("实例已经激活！")
                }

            },
            handleImage(row){
                const _this=this
                _this.dialogFormVisible=true;
                _this.processInstanceId=row.processInsId;


            },
            goBack(){
                this.dialogFormVisible=false;
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
            deleteProcIns(row){
                const _this=this;
                let url='http://localhost:8011/activiti/processIns/deleteProc/'+row.processInsId;
                console.log(url)
                _this.axios.get(url).then(function (resp) {
                    if (resp.status===200){
                       alert(resp.data)
                        window.location.reload();
                    }
                })

            }

        },
        created() {
            const _this=this
            let url='http://localhost:8011/activiti/processIns/findAllProcIns';
            _this. axios.get(url).then(function(resp) {
                if(resp.status===200){
                    console.log(resp.data)
                    _this.tableData=resp.data}
            })

        }
    }
</script>

<style scoped>

</style>
