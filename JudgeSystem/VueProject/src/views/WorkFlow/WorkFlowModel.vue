<template>
    <div class="app-container">
    <el-card>
        <div class="filter-container" align="right">
            <!-- <el-input
                     v-model="query.name"
                     placeholder="模型名称"
                     style="width: 200px;"
                     class="filter-item"
             />
             <el-button class="filter-item" type="primary" icon="el-icon-search" @click="queryList">搜索</el-button>-->
            <el-button
                    class="filter-item"
                    style="margin-left: 10px;"
                    type="success"
                    icon="el-icon-plus"
                    @click="handleCreate"
            >添加</el-button>
        </div>
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
            <el-table-column label="ID" prop="id" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column label="模型名称" prop="name" align="center" width="150">
                <template slot-scope="scope">
                    <span>{{ scope.row.name }}</span>
                </template>
            </el-table-column>
            <el-table-column label="KEY" prop="key" align="center" width="150">
                <template slot-scope="scope">
                    <span>{{ scope.row.key }}</span>
                </template>
            </el-table-column>
            <el-table-column label="版本" prop="version" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.version }}</span>
                </template>
            </el-table-column>
            <!-- <el-table-column label="部署ID" prop="deploymentId" align="center">
                 <template slot-scope="scope">
                     <span>{{ scope.row.deploymentId }}</span>
                 </template>
             </el-table-column>-->
            <el-table-column label="状态" prop="deploymentId" align="center">
                <template slot-scope="scope">
            <span v-if="scope.row.deploymentId !== null">
              <el-tag type="success">已部署</el-tag>
            </span>
                    <span v-else>
              <el-tag type="info">未部署</el-tag>
            </span>
                </template>
            </el-table-column>
            <el-table-column label="创建时间" prop="createTime" align="center" width="250">
                <template slot-scope="scope">
                    <span>{{ scope.row.createTime }}</span>
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
                    <el-tooltip content="流程定义" placement="top">
                        <i class="el-icon-s-marketing operate-edit" @click="handleDraw(row)" />
                    </el-tooltip>
                    <el-tooltip content="部署" placement="top">
                        <i class="el-icon-s-promotion operate-edit" @click="handleDeploy(row)" />
                    </el-tooltip>
                    <!--<el-tooltip content="挂起" placement="top">
                        <i class="el-icon-edit-outline operate-edit" @click="handleUpdate(row)" />
                    </el-tooltip>-->
                    <el-tooltip content="删除" placement="top">
                        <i class="el-icon-delete-solid operate-delete" @click="handleDelete(row)" />
                    </el-tooltip>
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

        <el-dialog
                :title="title"
                width="35%"
                top="5vh"
                :visible.sync="showDialog"
                :close-on-click-modal="false"
                @close="cancel"
        >
            <model-edit v-if="showDialog" ref="modelForm" :model-id="modelId" />
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancel">取消</el-button>
                <el-button type="primary" @click="submit">确定</el-button>
            </div>
        </el-dialog>
    </el-card>
</div>
</template>

<script>

  import Pagination from '../../components/Pagination/index'
  import ModelEdit from '../../components/model/edit'
  import { MessageBox } from 'element-ui'

    export default {
        name: 'workflow',
        components: { Pagination, ModelEdit },
        data() {
            return {
                title: '创建模型',
                pagesize: 5,
                currentPage:1,
                tableData:[],
                loading: false,
                showDialog: false,
                tableKey: 1,
                workflow:{
                    list:[]
                },
                list:[],
                responseResult: {
                    code: '',
                    data: '',
                    msg: ''
                },
                modelId: '',
                actUrl: 'http://localhost:8011/activiti/static/modeler.html?'
            }
        },
        computed: {
           /* ...mapState({
                workflow: state => state.workflow
            })*/
        },
        mounted() {
           // this.queryList()
        },
        methods: {
            //...mapActions('workflow', ['getList', 'deleteModel', 'deploy']),
            /*queryList() {
               /!* const self = this
                this.loading = true
                this.getList({
                    ...self.query,
                    success: () => {
                        self.loading = false
                    }
                })*!/
            },*/
            handleDraw(row) {
                window.open(this.actUrl + `modelId=${row.id}`)
            },
            handleDeploy(row) {
                const _this = this
                MessageBox.confirm('您确定要部署该模型吗？', '确认部署', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let url='http://localhost:8011/activiti/model/'+row.id+'/deployment';
                   _this.axios.get(url).then(function (resp) {
                      _this.responseResult=resp.data
                      // console.log(resp.data)
                       alert('code:'+_this.responseResult.code+'  message:'+_this.responseResult.msg)
                       window.location.reload();

                   })
                })
            },
            handleCreate() {
                this.title = '创建模型'
                this.showDialog = true
            },
            handleUpdate(row) {
                let url='http://localhost:8011/activiti/model/sus/'+row.key;
                    this.axios.get(url).then(function (resp) {
                        console.log(resp.data)
                    })
            },
            handleDelete(row) {
                const _this = this
                MessageBox.confirm('您确定要删除该模型吗？', '确认删除', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let url='http://localhost:8011/activiti/model/deleteById/'+row.id;
                    _this.axios.delete(url).then(function (resp) {
                        alert(resp.data.msg)
                        window.location.reload();
                    })
                })
            },
            cancel() {
                this.modelId = ''
                this.showDialog = false
                this.$refs.modelForm.resetForm()
            },
            submit() {
                const self = this
                this.$refs.modelForm.submitForm(() => {
                    self.showDialog = false
                    // self.queryList()
                    window.location.reload()
                })
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
            let url='http://localhost:8011/activiti/model/findAll';
            _this. axios.get(url).then(function(resp) {
                if(resp.status===200){
                    console.log(resp.data)
                    _this.tableData=resp.data}
            })

        }
    }
</script>


