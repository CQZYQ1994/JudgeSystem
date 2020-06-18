<template>
    <div>
        <el-card>
        <div>
            <el-dialog title="通知详情" :visible.sync="dialogFormVisible">
                <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                    <el-form-item label="通知主题" >
                        <el-input v-model="ruleForm.notifi_name" readonly="ture"></el-input>
                    </el-form-item>
                    <el-form-item label="通知描述">
                        <el-input type="textarea" v-model="ruleForm.notifi_desc" readonly="ture"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间" >
                        <el-date-picker
                                readonly="ture"
                                v-model="ruleForm.start_time"
                                type="date"
                                placeholder="选择日期时间"
                                format="yyyy 年 MM 月 dd 日"
                                value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="截止时间" >
                        <el-date-picker
                                readonly="ture"
                                v-model="ruleForm.end_time"
                                type="date"
                                placeholder="选择日期时间"
                                format="yyyy 年 MM 月 dd 日"
                                value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>
                    <!-- <el-form-item label="创建时间" prop="create_time">
                         <el-date-picker
                                 v-model="ruleForm.create_time"
                                 type="datetime"
                                 placeholder="选择日期时间"
                                 format="yyyy年MM月dd日 HH:mm:ss"
                                 value-format="yyyy-MM-dd HH:mm:ss">
                         </el-date-picker>
                     </el-form-item>-->

                    <el-form-item label="创建人" >
                        <el-input v-model="ruleForm.create_user" readonly="ture"></el-input>
                    </el-form-item>
                    <el-form-item label="文件路径" >
                        <el-input v-model="ruleForm.document"></el-input>
                    </el-form-item>


                    <el-form-item>
                        <el-button @click="goback">返回</el-button>
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
                    prop="notifi_name"
                    label="通知"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="notifi_desc"
                    label="内容描述"
                    width="220">
            </el-table-column>
            <el-table-column
                    prop="start_time"
                    label="开始时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="end_time"
                    label="截止时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="create_time"
                    label="发布时间"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="create_user"
                    label="创建人"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="document"
                    label="附件资料"
                    width="120">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="lookInformation(scope.row)" icon="el-icon-search" type="primary" circle size="small" ></el-button>
                    <el-button @click="downloadfile(scope.row)" icon="el-icon-download" type="primary" circle size="small" ></el-button>

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
            downloadfile(row){
                const _this=this
                _this.ruleForm=row;
                let fileName=_this.ruleForm.document;
                console.log(fileName);
                let url='http://localhost:8018/file/download/'+fileName;
                console.log(url);
                window.location.href = url;
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
            }
        },

        data() {
            return {
                tableData1: [],
                tableData2:'',
                pagesize: 5,
                currentPage:1,
                dialogFormVisible:false,
                ruleForm: {
                    id:'',
                    notifi_name:'',
                    notifi_desc:'',
                    start_time:'',
                    end_time:'',
                    create_time:'',
                    create_user:'',
                    del_sign:'',
                    document:'',

                },
                rules: {
                    notifi_name: [
                        { required: true, message: '请输入通知名', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    notifi_desc: [
                        { required: true, message: '请输入招聘人数', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],

                    start_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    end_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],

                    create_user: [
                        { required: true, message: '请输入联系人', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],


                },
            }
        },
        created() {
            const _this=this
            let url='http://localhost:8014/notify/findAll';
            _this.axios.get(url).then(function(resp){
                _this.tableData1=resp.data
            })
        }
    }
</script>
