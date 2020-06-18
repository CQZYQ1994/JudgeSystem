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
                <el-table-column label="流程定义id"  align="center" width="150" fixed>
                    <template slot-scope="scope">
                        <span>{{ scope.row.processDefId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程定义名称" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processDefName }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程定义部署id" align="center" width="200">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processDefDepId }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程定义标识KEY" align="center" width="150">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processDefKey }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="流程定义版本" align="center" width="120">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processDefVersion }}</span>
                    </template>
                </el-table-column>
                 <el-table-column label="流程定义资源名" align="center" width="200">
                     <template slot-scope="scope">
                         <span>{{ scope.row.processDefResName }}</span>
                     </template>
                 </el-table-column>
                <el-table-column label="流程定义状态" align="center" width="120">
                    <template slot-scope="scope">
            <span v-if="!scope.row.isSuspended">
              <el-tag type="success">已激活</el-tag>
            </span>
                        <span v-else>
              <el-tag type="info">已暂停</el-tag>
            </span>
                    </template>
                </el-table-column>
              <el-table-column label="流程定义图资源名" align="center" width="250">
                    <template slot-scope="scope">
                        <span>{{ scope.row.processDefDiaResName }}</span>
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
                        <el-button type="primary" @click="handleUpdate(row)" size="small">状态切换</el-button>

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
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "WorkFlowProcess",
        data() {
            return {
                pagesize: 5,
                currentPage:1,
                tableData:[],
                loading: false,
                list:[],
                tableKey:1,
                responseResult: {
                    code: '',
                    data: '',
                    msg: ''
                },
                processDefinitionId:'',
                status:'',
                ProcessDefInfo:{
                    processDefDepId: '',
                    processDefDiaResName: '',
                    processDefId: '',
                    processDefKey: '',
                    processDefName: '',
                    processDefResName: '',
                    processDefVersion: '',
                    isSuspended:'',

                }
            }
        },

        methods: {



            handleUpdate(row) {
                const _this=this
                if(row.isSuspended===true){
                    _this.status="active";
                    _this.processDefinitionId=row.processDefId;
                    console.log(_this.processDefinitionId)
                    console.log(_this.status)
                    _this.changeProcessDefStatus(_this.processDefinitionId,_this.status);
                }else {
                    _this.status="suspend";
                    _this.processDefinitionId=row.processDefId;
                    console.log(_this.processDefinitionId)
                    console.log(_this.status)
                    _this.changeProcessDefStatus(_this.processDefinitionId,_this.status);
                }

            },
            handleSizeChange: function (size) {
                this.pagesize = size;
                console.log(this.pagesize)
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)
            },
            changeProcessDefStatus(processDefinitionId,status){
                let url='http://localhost:8011/activiti/processDef/procDef/'+status+'/'+processDefinitionId;
                this.axios.get(url).then(function (resp) {
                    console.log(resp.data)
                    alert(resp.data)
                })
            }
        },
        created() {
            const _this=this
            let url='http://localhost:8011/activiti/processDef/findProcDef';
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
