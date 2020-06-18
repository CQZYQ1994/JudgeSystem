<template>
    <div>
        <el-card>
        <div>
            <el-dialog title="详细结果" :visible.sync="dialogFormVisible">
                <el-form style="width: 60%" :model="ruleForm"  ref="ruleForm" label-width="120px" class="demo-ruleForm">
                    <el-form-item label="申请人姓名" >
                        <el-input v-model="ruleForm.name" readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item label="申请主题" >
                        <el-input v-model="ruleForm.topic" readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item label="流程定义Key" >
                        <el-input v-model="ruleForm.processDefKey" readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item label="流程实例id" >
                        <el-input v-model="ruleForm.processInsId" readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item label="描述" >
                        <el-input type="textarea" v-model="ruleForm.description" readonly="true"></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间" >
                        <el-date-picker
                                readonly="true"
                                v-model="ruleForm.create_time"
                                type="datetime"
                                placeholder="选择日期时间"
                                format="yyyy年MM月dd日 HH:mm:ss"
                                value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="修改时间" >
                        <el-date-picker
                                readonly="true"
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
                                   inactive-text="未审" readonly="true"></el-switch>
                    </el-form-item>
                    <el-form-item label="评审意见" >
                    <el-input type="textarea" v-model="ruleForm.review_opinion" readonly="true"></el-input>
                </el-form-item>
                    <el-form-item label="评审结果" >
                    <el-input v-model="ruleForm.review_result" readonly="true"></el-input>
                </el-form-item>
                    <el-form-item label="评审得票" >
                        <el-input v-model="ruleForm.review_vote" readonly="true"></el-input>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>
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
            <el-table-column label="申请状态" align="center" width="120">
                <template slot-scope="scope">
            <span v-if="scope.row.processInsId!==null">
              <el-tag type="success">审核中</el-tag>
            </span>
                    <span v-else>
              <el-tag type="info">未提交</el-tag>
            </span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="processDefKey"
                    label="流程定义Key"
                    width="200">
            </el-table-column>
           <!-- <el-table-column
                    label="审核状态"
                    width="100">
                <template slot-scope="scope">
                    <el-button type="text"   size="small" v-if="!scope.row.review_status">未审核</el-button>
                    <el-button type="text"   size="small" v-else>已审核</el-button>
                </template>
            </el-table-column>-->
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
                    fixed="right"
                    label="操作"
                    width="150">
                <template slot-scope="scope">
                    <el-button @click="editClick(scope.row)" icon="el-icon-search" type="primary" circle size="small" ></el-button>
                    <el-button @click="createProcAndSubmit(scope.row)"  type="primary"  size="small" >提交申请</el-button>
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
            editClick(row){
                this.dialogFormVisible=true;
                this.ruleForm=row;
            },
            createProcAndSubmit(row){
                const _this=this;
             /*   _this.ruleForm.processInsId=25017;
                console.log(_this.processInstanceId)
                console.log(_this.ruleForm.processInsId)
                _this.update(_this.ruleForm);*/
             let url='http://localhost:8011/activiti/review/startFlow/'+row.processDefKey+'/'+row.id+'/'+row.name;
                _this.axios.get(url).then(function (resp) {
                    _this.processInstanceId=resp.data;
                    _this.ruleForm=row;
                    if (_this.processInstanceId!==null){
                        _this.ruleForm.processInsId=_this.processInstanceId;
                       /* console.log(_this.processInstanceId)
                        console.log(_this.ruleForm.processInsId)*/
                        _this.update(_this.ruleForm);
                    }
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
            update(ruleForm){
                const _this=this;
                let url='http://localhost:8015/apply/update';
                _this.axios.put(url,ruleForm).then(function (resp) {
                   if (resp.status===200){ window.location.reload();}
                })

            }

        },

        data() {
            return {
                tableData1: [],
                pagesize: 5,
                currentPage:1,
                processInstanceId:'',
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
                    processDefKey:'',
                    processInsId:'',
                    user_id:JSON.parse(window.localStorage.getItem('token' || '[]')).id,
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
            }
        },
        created() {
            const _this=this
            let ID=JSON.parse(window.localStorage.getItem('token' || '[]')).id;
            let url='http://localhost:8015/apply/findByUserId/'+ID;
            _this.axios.get(url).then(function(resp){
                _this.tableData1=resp.data

            })
        }
    }
</script>
