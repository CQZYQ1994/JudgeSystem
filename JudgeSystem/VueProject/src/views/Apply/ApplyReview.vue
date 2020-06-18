<template>
    <div>
        <el-card>
        <el-dialog title="详细结果" :visible.sync="dialogFormVisible">
            <el-form style="width: 80%" :model="ruleForm"  ref="ruleForm" label-width="120px" class="demo-ruleForm">
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
            </el-form>
        </el-dialog>
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
                    prop="document"
                    label="文件"
                    width="200">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="180">
                <template slot-scope="scope">
                    <el-button @click="downloadfile(scope.row)" icon="el-icon-download" type="primary" circle size="small" ></el-button>
                    <el-button @click="lookInformation(scope.row)" type="primary" icon="el-icon-edit"  size="small">查看详情</el-button>
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
            lookInformation(row){

                this.dialogFormVisible=true;
                this.ruleForm=row;


            },
            handleSizeChange: function (size) {
                this.pagesize = size;
                console.log(this.pagesize)
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)
            },
            downloadfile(row){
                const _this=this
                _this.ruleForm=row;
                let fileName=_this.ruleForm.document;
                console.log(fileName);
                let url='http://localhost:8018/file/download/'+fileName;
                console.log(url);
                window.location.href = url;
            },


        },

        data() {
            return {
                tableData1: [],
                pagesize: 5,
                currentPage:1,
                dialogFormVisible:false,
                dialogFormVisible2:false,
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
