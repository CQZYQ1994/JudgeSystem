<template>
    <div>
        <el-card>
        <el-table
            :data="tableData1.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            border
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
                    label="相关文件"
                    width="200">
            </el-table-column>
        <el-table-column
                fixed="right"
                label="操作"
                width="180">
            <template slot-scope="scope">
                <el-button @click="lookInformation(scope.row)" icon="el-icon-search"  type="primary" circle size="small" :disabled="dialogFormVisible"></el-button>
                <el-button @click="downloadfile(scope.row)" icon="el-icon-download" type="primary" circle size="small" ></el-button>
                <el-button @click="editClick(scope.row)" type="primary" icon="el-icon-edit"  size="small">评审</el-button>
            </template>
        </el-table-column>
    </el-table>
        <div>
            <div><el-dialog title="查看详细" :visible.sync="dialogFormVisible">
            <div class="row" id="pdfDom" style="padding-top: 25px;background-color:#fff;">
            <el-form style="width: 80%" :model="ruleForm"  ref="ruleForm" label-width="120px" class="demo-ruleForm">
                <table border="1" cellspacing="0" cellpadding="1">
                    <tr>
                        <td>申请人：</td>
                        <td>{{ruleForm.name}}</td>
                    </tr>

                    <tr>
                        <td>申请类别：</td>
                        <td>{{ruleForm.topic}}</td>
                    </tr>

                    <tr>
                        <td>描述：</td>
                        <td>{{ruleForm.description}}</td>
                    </tr>

                    <tr>
                        <td>评审状态：</td>
                        <td>{{ruleForm.review_status}}</td>
                    </tr>

                    <tr>
                        <td>申请人</td>
                        <td>{{ruleForm.name}}</td>
                    </tr>

                    <tr>
                        <td>评审意见：</td>
                        <td>{{ruleForm.review_opinion}}</td>
                    </tr>

                    <tr>
                        <td>评审得票：</td>
                        <td>{{ruleForm.review_vote}}</td>
                    </tr>
                    <tr>
                        <td colspan="2">评审结果</td>
                    </tr>
                    <tr>
                        <td colspan="2">{{ruleForm.review_result}}</td>
                    </tr>

                    <tr>
                        <td>申请创建时间：</td>
                        <td>{{ruleForm.create_time}}</td>
                    </tr>
                    <tr>
                        <td>评审时间：</td>
                        <td>{{ruleForm.update_time}}</td>
                    </tr>

                </table>
               <!-- <el-form-item label="申请人">
                    <el-input v-model="ruleForm.name" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="申请类别" >
                    <el-input v-model="ruleForm.topic" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="描述" >
                    <el-input type="textarea" v-model="ruleForm.description" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="评审状态" prop="review_status">
                    <el-switch v-model="ruleForm.review_status"></el-switch>
                </el-form-item>

                <el-form-item label="评审意见" >
                    <el-input type="textarea" v-model="ruleForm.review_opinion" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="评审得票" >
                    <el-input v-model="ruleForm.review_vote" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="评审结果" >
                    <el-input v-model="ruleForm.review_result" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="申请创建时间">
                    <el-date-picker
                            readonly="true"
                            v-model="ruleForm.create_time"
                            type="datetime"
                            placeholder="选择日期时间"
                            format="yyyy年MM月dd日 HH:mm:ss"
                            value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="评审时间">
                    <el-date-picker
                            readonly="true"
                            v-model="ruleForm.update_time"
                            type="datetime"
                            placeholder="选择日期时间"
                            format="yyyy年MM月dd日 HH:mm:ss"
                            value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </el-form-item>-->
            </el-form>
            </div>
            <el-button type="button" class="btn btn-primary"v-on:click="getPdf()">导出PDF</el-button>
                <el-button @click="goback">返回</el-button>
        </el-dialog>
        </div>

        </div>
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
                this.$router.push({
                    path:'/ReviewUpdate',
                    query:{
                        id:row.id
                    }
                })
            },
            lookInformation(row){
                const _this=this
                _this.dialogFormVisible=true
                    _this.ruleForm = row;


            },
            handleSizeChange: function (size) {
                this.pagesize = size;
                console.log(this.pagesize)
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)
            },
            goback(){
                this.dialogFormVisible=false;
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
                tableData2: [],
                pagesize: 5,
                currentPage:1,
                dialogFormVisible:false,
                dialogFormVisible1:false,
                ruleForm: {
                    id:'',
                    name:'',
                    topic:'',
                    description:'',
                    review_status:'',
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
                ruleForm1: {
                    id:'',
                    name:'',
                    topic:'',
                    description:'',
                    review_status:'',
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
                        { required: true, message: '请输入申请人姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5个字符', trigger: 'blur' }
                    ],
                    position: [
                        { required: true, message: '请输入申请职位', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    review_status: [
                        { required: true, message: '请输入评审状态', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                    ],
                    start_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    end_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    review_opinion: [
                        { required: true, message: '请输入评审意见', trigger: 'blur' },
                        { min: 1, max: 300, message: '长度在 1 到 300 个字符', trigger: 'blur' }
                    ],
                    review_result: [
                        { required: true, message: '请输入评审意见', trigger: 'blur' },
                        { min: 1, max: 300, message: '长度在 1 到 300 个字符', trigger: 'blur' }
                    ],
                    create_time: [
                        {  required: true, message: '请选择时间', trigger: 'change' }
                    ],
                    document_path: [
                        { required: true, message: '请上传附件', trigger: 'blur' }
                    ]
                },
                htmlTitle:'评审结果'
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
