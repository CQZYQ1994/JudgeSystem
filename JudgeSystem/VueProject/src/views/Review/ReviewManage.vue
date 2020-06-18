<!--
<template>
    <div>
        <el-card>
        <el-table
                :data="tableData1.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                border
                style="width: 100%">
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
                    prop="position"
                    label="申请职位"
                    width="100">
            </el-table-column>
            <el-table-column
                    label="评审状态"
                    width="110">
                <template slot-scope="scope">
                    <el-button type="text"   size="small" v-if="!scope.row.review_status">未审核</el-button>
                    <el-button type="text"   size="small" v-else>已审核</el-button>
                </template>
            </el-table-column>
            <el-table-column
                    prop="start_time"
                    label="建议评审开始时间"
                    width="140">
            </el-table-column>
            <el-table-column
                    prop="end_time"
                    label="建议评审截止时间"
                    width="140">
            </el-table-column>
            <el-table-column
                    prop="review_opinion"
                    label="评审意见"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="review_result"
                    label="评审得票"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="create_time"
                    label="评审创建时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="document_path"
                    label="简历附件"
                    width="100">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="editClick(scope.row)" icon="el-icon-edit" type="primary" circle size="small" ></el-button>
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
            deleteJob(row){
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this=this
                    axios.delete('http://localhost:8013/judge/deleteById/'+row.id).then(function(resp){
                       /* _this.$alert(row.id+'删除成功!', 'OK', {
                            confirmButtonText: '确定',
                            callback: action => {*/
                                window.location.reload()
                       /*     }
                        });*/
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
            handleSizeChange: function (size) {
                this.pagesize = size;
                console.log(this.pagesize)
            },
            handleCurrentChange: function(currentPage){
                this.currentPage = currentPage;
                console.log(this.currentPage)
            },
            changeSwitch(data){
                console.log(data)
            }
        },

        data() {
            return {
                tableData1: [],
                pagesize: 5,
                currentPage:1
            }
        },
        created() {
            const _this=this
            axios.get('http://localhost:8013/judge/findAll').then(function(resp){
                _this.tableData1=resp.data
            })
        }
    }
</script>
-->
